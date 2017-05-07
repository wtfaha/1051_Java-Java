

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public interface MapConfig {
	//素材的大小  
	int eleWidth = 50;
	int eleHeight = 50;
	//地图的大小 
	int MapWidth = 2000;
	int MapHeight= 2000;
	//地图保存的位置
	String path = JOptionPane.showInputDialog("請輸入地圖檔案路徑\n例:C:\\\\Users\\\\chi\\\\Desktop\\\\map1.map");
	
	
	//游戏素材大小
	int elesize = 50;

	
	//用到的图片素材
	ImageIcon icon0 = new ImageIcon("000空白.png"); 
	ImageIcon icon1 = new ImageIcon("001草地.png");  
	ImageIcon icon2 = new ImageIcon("002地砖.png"); 
	ImageIcon icon3 = new ImageIcon("003召泽地板副本.png"); 
	ImageIcon icon4 = new ImageIcon("004黑洞.png"); 
    ImageIcon icon100 = new ImageIcon("100红树.png");  
    ImageIcon icon101 = new ImageIcon("101绿树.png");  
    ImageIcon icon102 = new ImageIcon("102绿竹.png");  
    ImageIcon icon103 = new ImageIcon("103高绿树.png");
    ImageIcon icon104 = new ImageIcon("104枯樹.png");
    ImageIcon icon150 = new ImageIcon("150岩浆.png");
    //将所有的图片素材对象放入一个数组中，便于窗体上的下拉列表添加所有的图片素材 
	ImageIcon[] allicons = {icon0,icon1,icon2,icon3,icon4,icon100,icon101,icon102,icon103,icon104,icon150}; 
}
