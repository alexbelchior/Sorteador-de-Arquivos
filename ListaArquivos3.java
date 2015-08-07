import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;

public class ListaArquivos3 extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JFrame janela;
	static int totalArquivos = 0;
	static List<File>arquivo = new ArrayList<File>();
	static int posicao = 0;
	static int sorteado = 0;
	
    public static void main(String[] args) throws Exception {  
    	
    	String caminho = javax.swing.JOptionPane.showInputDialog("digite o diretorio a ser listado");  
        
        File raiz = new File(caminho);  
        listaDiretorio(raiz);
        totalArquivos = arquivo.size();
        System.out.println(totalArquivos);
 
        geradorAleatorio(totalArquivos);
        javax.swing.JOptionPane.showMessageDialog(null,"Diretório com um total de " + totalArquivos + " arquivos");
        javax.swing.JOptionPane.showMessageDialog(null,"O arquivo sorteado foi o de número " + arquivo.indexOf(arquivo.get(sorteado)) + " \n" + arquivo.get(sorteado).getAbsolutePath());
        
        String texto = arquivo.get(sorteado).getAbsolutePath(); //Passa para a String o caminho do arquivo sorteado
        
        //--------------------------------------------------------------//
        //--Verifica se o diretório onde é salvo o histórico existe
        File diretorio = new File("C:\\temp_sorteador_arquivo");
        if(!diretorio.exists()){
        	diretorio.mkdir();
        }
        //--------------------------------------------------------------//
        
        File arquivo = new File ("C:\\temp_sorteador_arquivo\\historico_sorteador_arquivos.txt");
        
        Date data = new Date();  
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy"); 
        formatador.format(data);
        
        System.out.println(data);
        
        try {

        if (!arquivo.exists( ) ) {
        arquivo.createNewFile( );
        }
        FileWriter fw = new FileWriter (arquivo, true);//escreve no final do arquivo, sem sobrescreve-lo
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(data + "\n");
        bw.newLine();
        bw.newLine();
        bw.write(texto);
        bw.newLine();
        bw.newLine();
        bw.write("\n----------------------------------");
        bw.newLine();
        bw.newLine( );
        bw.close( );
        fw.close( );

        }catch(FileNotFoundException e){
        	
        	System.out.println(e.getStackTrace());
        }
        
    }
    
    public static void geradorAleatorio(int total){
    	total = total + 1;
    	Random random = new Random();
    	sorteado = random.nextInt(total);
    	
    }
      
    public static void listaDiretorio(File raiz) {  
          
        for(File f: raiz.listFiles()) {  
            if(f.isFile()){   
                arquivo.add(f);
            	posicao++;
            }
            else{  
                listaDiretorio(f);
            }
        }
    }  
}  
