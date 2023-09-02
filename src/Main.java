import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.Buffer;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main implements KeyListener{

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key Pressed");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public Main(){

    }

    public static void paint(JFrame frame, BufferedImage image, int maxIterations, ComplexNumber centre, double zoomWidth, int paletteLength){

        int imgWidth = image.getWidth();
        int imgHeight = image.getHeight();
        for(int x = 0; x < imgWidth; ++x){
            for(int y = 0; y < imgHeight; ++y){
                double a = centre.getReal() + (double)x*zoomWidth/imgWidth - 0.5*zoomWidth;
                double b = (double)y*zoomWidth/imgWidth - 0.5*zoomWidth*imgHeight/imgWidth - centre.getImaginary();
                ComplexNumber c = new ComplexNumber(-1.77,0.004);
                ComplexNumber z = new ComplexNumber(a,b);
                for(int i = 0; i < maxIterations; ++i) {
                    z = z.multiply(z).add(c);
                    if(z.magnitude() > 2){
                        //System.out.print("@");
                        image.setRGB(x,y,Color.HSBtoRGB((float)i/paletteLength,1,1));
                        break;
                    }
                }
                if(z.magnitude() <= 2){
                    //System.out.print(" ");
                    image.setRGB(x,y,new Color(0,0,0).getRGB());
                }
                frame.repaint();

            }

            //System.out.print("\n");
        }




    }

    public static void main(String[] args) {

        BigDecimal a = new BigDecimal(0.000010000);
        BigDecimal b = a.multiply(a).add(a.multiply(new BigDecimal(2.123)));

        System.out.println(a);
        System.out.println(a.unscaledValue());
        System.out.println(a.scale());

        System.out.println(b);
        System.out.println(b.unscaledValue());
        System.out.println(b.scale());

        System.out.println(b.compareTo(new BigDecimal(0.0000213)));

        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.print("Hello and welcome!\n");

        Main main = new Main();

        JFrame f = new JFrame(); //Creating new instance of JFrame
        f.addKeyListener(main);

        int imgWidth = 1080;
        int imgHeight = 720;


        BufferedImage img = new BufferedImage(imgWidth,imgHeight,BufferedImage.TYPE_INT_RGB);

        JLabel label = new JLabel(new ImageIcon(img));
        f.add(label);

        f.setSize(imgWidth,imgHeight);
        f.setVisible(true);
        f.setLayout(null);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ComplexNumber centre = new ComplexNumber(0,0);
        double zoomWidth = 4;
        int maxIterations = 0;
        int paletteLength = 250;

        ComplexNumber c = new ComplexNumber(-1.75666169598460155434,0.00);
        ComplexNumber z = c;

        /*
xmin: -1.76904264188278848999
xmax: -1.76904262777396318057
ymin: -0.00315594235842978009
ymax: -0.00315592922876584861


*/

        paint(f,img,maxIterations,centre,zoomWidth,paletteLength);

        Graphics g = img.getGraphics();
        g.setColor(new Color(255,255,255));
        //g.fillOval(100,100,5,5);
        zoomWidth /= 6;
        for (int i = 0; i < 1080; ++i){
            maxIterations += 1;
            paint(f,img,maxIterations,centre,zoomWidth,paletteLength);
        }

        f.repaint();

        /*try{
            File outputFile = new File("img.png");
            ImageIO.write(img,"png",outputFile);
        }catch (IOException e){
        }*/

    }
}