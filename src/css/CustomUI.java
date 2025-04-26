package css;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class CustomUI {
	private static final Color COFFEE = new Color(111, 78, 55);  // nâu sữa đậm
	private static final int BORDER_RADIUS = 20;
	public static void applyCustomUI() {
    	 // Tùy chỉnh cho JFrame
		 UIManager.put("Frame.background", Color.WHITE);  // Nền trắng
		 UIManager.put("Frame.titleFont", new Font("Arial", Font.BOLD, 20));  // Font tiêu đề
		
		 // Tùy chỉnh cho JLabel
		 UIManager.put("Label.foreground", COFFEE);  // Màu chữ
		 UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 13));  // Font chữ
		
		 // Tùy chỉnh cho JTextField
    	 UIManager.put("TextField.border", new LineBorder(COFFEE, 2, true));
         UIManager.put("TextField.foreground", COFFEE);  // Màu chữ
        

         // Tùy chỉnh cho JPasswordField
         UIManager.put("PasswordField.border", new LineBorder(COFFEE, 2, true));
         UIManager.put("PasswordField.foreground", COFFEE);
        
         // Tùy chỉnh cho JTextArea
         UIManager.put("TextArea.border", new LineBorder(COFFEE, 2, true));
         UIManager.put("TextArea.foreground", COFFEE);
        

         // Tùy chỉnh cho JComboBox
         UIManager.put("ComboBox.border", new LineBorder(COFFEE, 2, true));
         UIManager.put("ComboBox.foreground", COFFEE);
         

         // Tùy chỉnh cho JButton
         UIManager.put("Button.border", new LineBorder(COFFEE, 2, true));
         UIManager.put("Button.foreground", Color.WHITE);
         UIManager.put("Button.background", COFFEE);
         UIManager.put("Button.focus", new Color(0, 0, 0, 0)); 
         UIManager.put("Button.select", new Color(91, 64, 45)); // Màu nền khi nhấn nút
         
         // Tùy chỉnh cho JTable
         UIManager.put("Table.background", Color.WHITE);
         UIManager.put("Table.foreground", COFFEE);
         UIManager.put("Table.gridColor", COFFEE);
         UIManager.put("Table.font", new Font("Arial", Font.PLAIN, 13));
         UIManager.put("TableHeader.background", COFFEE);
         UIManager.put("TableHeader.foreground", Color.WHITE);
         
         // Tùy chỉnh cho JScrollPane
         
         
    }
    
}
