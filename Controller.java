package application;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainController {
    @FXML
    private TextField nam;
    @FXML
    private TextField usn;
    @FXML
    private TextField contact;
    @FXML
    private TextField extra;

    public void try1(ActionEvent event) throws IOException {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Random rand=new Random();
        int my=rand.nextInt(50)+1;
        int my1=rand.nextInt(100)+1;
        int sno=1;
        String name=" sno : \t\t"+sno+ "\n Name : \t\t"+nam.getText()+"\n S Number : \t\t"+my+"\n USN : \t\t"+usn.getText()+"\n Contact : \t\t"+contact.getText()+"\n Serial number : \t\t"+my1+"\n extra : \t\t"+extra.getText();
        String myWeb = name;
        int width = 400;
        int height = 400;
        //String fileType = "png";

        BufferedImage bufferedImage = null;
        try {

            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            // System.out.println("Success...");

        } catch (WriterException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        nam.setText("");
        usn.setText("");
        contact.setText("");
        extra.setText("");

        ImageView qrView = new ImageView();
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        Stage primaryStage = new Stage();
        StackPane root = new StackPane();
        root.getChildren().add(qrView);

        Scene scene = new Scene(root, 400, 400);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}

		/*Parent root = FXMLLoader.load(getClass().getResource("qrtest.fxml"));
    	Scene scene = new Scene(root,400,400);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
}*/