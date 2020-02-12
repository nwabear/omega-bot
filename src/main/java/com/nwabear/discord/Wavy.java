package com.nwabear.discord;

import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

public class Wavy extends Command {
    public Wavy(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";wavy <iterations>: makes the most recent image wavy";
    }

    @Override
    public void run() {
        int iters = 1;
        try {
            iters = Integer.parseInt(message.getContentRaw().split(" ")[1]);
        } catch(Exception e) {
            // do nothing
        }
        MessageHistory history = this.channel.getHistory();
        List<Message> messages = history.retrievePast(20).complete();

        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_3BYTE_BGR);

        for(int i = 0; i < messages.size(); i++) {
            try {
                if (messages.get(i).getAttachments().get(0).isImage()) {
                    Message.Attachment atc = messages.get(i).getAttachments().get(0);
                    img = ImageIO.read(atc.getInputStream());
                    break;
                }
            } catch(Exception e) {
                System.out.println(e);
            }
        }

        for(int i = 0; i < iters; i++) {
            img = this.rotateImageByDegrees(img, 90);
            img = Rescaler.rescaleImage(img, img.getWidth() / 4);
            img = this.rotateImageByDegrees(img, -90);
            img = Rescaler.rescaleImage(img, img.getWidth() / 4);
        }

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "png", baos);
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            this.channel.sendFile(is, "output.png").queue();
        } catch(Exception e) {
            this.channel.sendMessage("Error distorting image").queue();
        }
    }

    public BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return rotated;
    }
}