package com.nwabear.discord;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.effect.FloatMap;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

public class Wavy extends Command {
    public Wavy(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";wavy: makes the most recent image wavy";
    }

    @Override
    public void command() {
        try {
            Platform.startup(() -> {});
        } catch(Exception e) {
            // do nothing
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MessageHistory history = Wavy.this.channel.getHistory();
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

                    System.out.println(messages.size());

                    DisplacementMap dm = new DisplacementMap();
                    FloatMap fm = new FloatMap();
                    fm.setWidth(img.getWidth());
                    fm.setHeight(img.getHeight());

                    for (int i = 0; i < img.getWidth(); i++) {
                        double v = (Math.sin(i / 20.0 * Math.PI) - 0.5) / 40.0;
                        for (int j = 0; j < img.getHeight(); j++) {
                            fm.setSamples(i, j, 0.0f, (float) v);
                        }
                    }

                    dm.setMapData(fm);

                    ImageView iv = new ImageView();
                    iv.setImage(SwingFXUtils.toFXImage(img, null));
                    Group g = new Group();
                    g.setEffect(dm);
                    g.getChildren().add(iv);

                    WritableImage distorted = g.snapshot(new SnapshotParameters(), new WritableImage(img.getWidth(), img.getHeight()));

                    File temp = new File("test.png");
                    if(temp.createNewFile()) {
                        System.out.println("created");
                    }

                    ByteArrayOutputStream os = new ByteArrayOutputStream();
                    ImageIO.write(SwingFXUtils.fromFXImage(distorted, null), "png", os);
                    ImageIO.write(SwingFXUtils.fromFXImage(distorted, null), "png", temp);
                    InputStream is = new ByteArrayInputStream(os.toByteArray());

                    Wavy.this.channel.sendFile(is, "distorted.png").queue();
                } catch(Exception e) {
                    Wavy.this.channel.sendMessage("Something went wrong, please check the syntax and try again").queue();
                    e.printStackTrace();
                }
            }
        });
    }
}