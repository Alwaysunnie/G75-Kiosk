import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.Random;

import javax.swing.JComponent;

public class VerCode  extends JComponent implements MouseListener {

    private String codes;  
    private int width, height = 40;  
    private int codesLength = 4;  
    private Random random = new Random(); 

    public VerCode() {
        width = this.codesLength * 16 + (this.codesLength - 1) * 10; 
        setPreferredSize(new Dimension(width, height));  
        setSize(width, height);  
        this.addMouseListener(this);
        setToolTipText("Click to replace the verification code.");
    }

    public int getCodesLength() {
        return codesLength;
    }

    public void setCodesLength(int codeLength) {
        if(codesLength < 4) {
            this.codesLength = 4;
        } else {
            this.codesLength = codeLength;
        }
    }

    public String getCode() {
        return codes;
    }

    public Color getRandColor(int min, int max) {
            if (min > 255)
                min = 255;
            if (max > 255)
                max = 255;
            int red = random.nextInt(max - min) + min;
            int green = random.nextInt(max - min) + min;
            int blue = random.nextInt(max - min) + min;
            return new Color(red, green, blue);
    }
           
        protected String generateCode() {
            char[] codes = new char[this.codesLength];
            for (int i = 0, len = codes.length; i < len; i++) {
                if (random.nextBoolean()) {
                    codes[i] = (char) (random.nextInt(10) + 48);
                } else {
                    codes[i] = (char) (random.nextInt(26) + 97);
                }
            }
            this.codes = new String(codes);
            return this.codes;
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(this.codes == null || this.codes.length() != this.codesLength) {  
                this.codes = generateCode();
            }
            width = this.codesLength * 16 + (this.codesLength - 1) * 10;
            super.setSize(width, height);  
            super.setPreferredSize(new Dimension(width, height));
            Font mFont = new Font("Arial", Font.BOLD | Font.ITALIC, 25);  
            g.setFont(mFont);  
            
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(getRandColor(200, 250));
            g2d.fillRect(0, 0, width, height);
            g2d.setColor(getRandColor(180, 200));
            g2d.drawRect(0, 0, width - 1, height - 1);
            
            int i = 0, len = 150;
            for (; i < len; i++) {
                int x = random.nextInt(width - 1);
                int y = random.nextInt(height - 1);
                int x1 = random.nextInt(width - 10) + 10;
                int y1 = random.nextInt(height - 4) + 4;
                g2d.setColor(getRandColor(180, 200));
                g2d.drawLine(x, y, x1, y1);
            }

            i = 0; len = this.codesLength;
            FontMetrics fm = g2d.getFontMetrics();
            int base = (height - fm.getHeight())/2 + fm.getAscent();
            for(;i<len;i++) {
                int b = random.nextBoolean() ? 1 : -1;
                g2d.rotate(random.nextInt(10)*0.01*b);
                g2d.setColor(getRandColor(20, 130));
                g2d.drawString(codes.charAt(i)+"", 16 * i + 10, base);
            }
        }

        public void nextCode() {
            generateCode();
            repaint();
        }

        public void mouseClicked(MouseEvent e) {

            nextCode();
        }

        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }
    }