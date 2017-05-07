package WarInterface;
import java.awt.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;


public class Dead extends JFrame
{
	Container ct;
	BackgroundPanel bgp;
	JLabel jlTitle, jPicture; 
	ImageIcon dead, ten_1, ten;
	Timer timer = new Timer();			
	
	public Dead()
	{
	   super("Bye Bye !!!");
	   ct = this.getContentPane();
	   this.setLayout(null);
	   
	   //背景圖
	   bgp = new BackgroundPanel((new ImageIcon("GUI/backgroundDead.jpg")).getImage());
	   bgp.setBounds(0,0,1000,750);
	   ct.add(bgp);
	   bgp.setLayout(null);
	   
	   //Title
	   jlTitle = new JLabel(); 
	   jlTitle.setText("¡¡Game Over¡¡");
       Font TitleFont = new Font("Blackadder ITC", Font.BOLD, 72);
       jlTitle.setForeground(Color.WHITE);
       jlTitle.setFont(TitleFont);
       //jlTitle.setBackground(Color.WHITE);
       jlTitle.setBounds(120, -100, 500, 500);
       bgp.add(jlTitle); 
	   
       //墓碑圖片
       dead = new ImageIcon("GUI/dead.png");
       jPicture = new JLabel(dead);
       jPicture.setBounds(110, 180, 500, 500);
       bgp.add(jPicture);  
       
       /*ten = new ImageIcon("10.png");
       jPicture = new JLabel(ten);
       jPicture.setBounds(300, 250, 500, 500);
       bgp.add(jPicture); 
       
       ten_1 = new ImageIcon("10-1.png");
       jPicture = new JLabel(ten_1);
       jPicture.setBounds(-150, 250, 500, 500);
       bgp.add(jPicture); */
       
	   this.setSize(700,700);
	   this.setLocation(0,0);
	   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   this.setLocationRelativeTo(null);
	   this.setVisible(true);
	   timer.schedule(new Change(this), 2500); //自動關掉Frame---->還是回到遊戲開始畫面?
	}
	
	
	class BackgroundPanel extends JPanel
	{
		Image im;
		public BackgroundPanel(Image im)
		{
		   this.im=im;
		   this.setOpaque(true);
		}
		//Draw the back ground.
		public void paintComponent(Graphics g)
		{
		   super.paintComponents(g);
		   g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);

		}

	}
	
	
}

