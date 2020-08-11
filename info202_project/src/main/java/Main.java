
import dao.ProductCollectionsDAO;
import dao.ProductDAO;
import dao.ProductJdbcDAO;
import gui.MainMenu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author geaja121
 */
public class Main {

	private static final ProductDAO pDAO = new ProductJdbcDAO();
//	private static final ProductDAO pDAO = new ProductCollectionsDAO();
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		MainMenu menu = new MainMenu(pDAO);
		menu.setLocationRelativeTo(null);
		menu.setVisible(true);
	}
	
}
