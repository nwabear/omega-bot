package com.nwabear.discord;

import ij.ImagePlus;
import ij.process.ImageProcessor;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageHistory;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

public class GXOrImg extends Command {
    public GXOrImg(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";gxor: creates a gif of every xor input in order (0-255)";
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
        try {
            File temp = new File("temp.gif");
            if(temp.createNewFile()) {
                System.out.println("file created");
            }
            ImageOutputStream ios = new FileImageOutputStream(temp);
            GifSequenceWriter gsw = new GifSequenceWriter(ios, iproc.getBufferedImage().getType(), 1, true);
            gsw.writeToSequence(iproc.getBufferedImage());
            for(int i = 1; i < 255; i++) {
                iproc.xor(i - 1);
                iproc.xor(i);
                gsw.writeToSequence(iproc.getBufferedImage());
            }
            gsw.close();
            ios.close();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ImageIO.write(ios, "gif", baos);
            this.channel.sendFile(temp, "output.gif").queue();
        } catch(Exception e) {
            this.channel.sendMessage("Image too big to send!").queue();
            e.printStackTrace();
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