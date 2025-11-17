import Vista.FrmMenuPrincipal; 
public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmMenuPrincipal objetoMenuPrincipal = new FrmMenuPrincipal();
                objetoMenuPrincipal.setVisible(true);
            }
        });
    }
}
