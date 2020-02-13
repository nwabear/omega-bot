package com.nwabear.discord;

import ij.ImagePlus;
import ij.process.ImageProcessor;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageHistory;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

public class XOrImg extends Command {
    public XOrImg(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";xor: puts image through an xor filter";
    }

    @Override
    public void run() {
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
                // do nothing
            }
        }

        this.channel.sendMessage("Processing...").queue();

        ImagePlus ip = new ImagePlus("image", img);
        ImageProcessor iproc = ip.getChannelProcessor();
        iproc.xor(87);
        img = iproc.getBufferedImage();

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