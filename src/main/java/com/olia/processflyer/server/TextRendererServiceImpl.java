/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olia.processflyer.server;

import com.olia.processflyer.client.TextRendererService;
import com.google.gwt.user.server.Base64Utils;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author HUETTM
 */
public class TextRendererServiceImpl extends RemoteServiceServlet implements TextRendererService {

    @Override
    public String getImageOfText(String text) {
        BufferedImage binaryImage = TextToImage.renderTextToPNG("Test 1");
        ByteArrayOutputStream imageOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(binaryImage, "PNG", imageOutputStream);
        } catch (IOException ex) {
            Logger.getLogger(TextRendererServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String imageAsBase64 = Base64Utils.toBase64(imageOutputStream.toByteArray());
        
        return imageAsBase64;
    }
    
}
