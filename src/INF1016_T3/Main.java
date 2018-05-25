package INF1016_T3;

import java.io.IOException;
import java.util.ArrayList;

public class Main 
{
	//----------VARIÁVEIS----------//
	static final	String 				ENDERECO_ARQUIVO_TEXTO	= "data\\Matriz.txt";
	static private	ArrayList<String> 	data		 			= null;
	static private 	String[]			matriz					= null;
	static private	int					tamanhoMatriz			= 0;

	//----------CONSTRUTOR----------//
	public static void main(String [ ] args)
	{
		//Importando as leituras do arquivo texto de valores da matriz
		leArquivoTexto();
		populaMatriz();
		calculaMaiorCaminho();
	}

	//----------MÉTODOS PRIVADOS----------//
	static private void leArquivoTexto()
	{
		ArchiveManager archiveManager = new ArchiveManager(ENDERECO_ARQUIVO_TEXTO);
		try 
		{
			data = archiveManager.openArchive();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static private void populaMatriz()
	{
		//Inicializando variáveis auxiliares
		int k=0;
		int row=0;

		if(data.isEmpty() == false)
		{
			//Loop de preenchimento da matriz
			while(k < data.size())
			{
				//Pegando uma linha do arquivo texto
				String stringData = data.get(k).toString();
				//					/*TEST PRINT*/System.out.printf(stringData+"\n");

				//Se ela for de tamanho da matriz...
				if(stringData.startsWith("{")==true)
				{
					//..inicializa a matriz com esse tamanho.
					tamanhoMatriz = Integer.parseInt(stringData.substring(1, 2));
					matriz = new String[tamanhoMatriz];
					for(int i=0; i<tamanhoMatriz; i++)
						matriz[i] = new String();
				}
				//Se ela for de valor da matriz...
				else if(stringData.startsWith("[")==true)
				{
					//...retira os '[]'s...
					String stringDataAux = stringData.substring(1, stringData.length()-1);
					//...separa os 'n' valores...
					String coordenadas[] = stringDataAux.split(",");
					//...adiciona todos eles na linha da matriz...
					for(int col=0; col<coordenadas.length; col++)
					{
						matriz[row].concat(coordenadas[col]);
					}
					//...e atualiza o valor auxiliar 'row'.
					row++;
				}
				k++;
			}
		}
	}

	static private void calculaMaiorCaminho()
	{		
		Matrix M = new Matrix(matriz);
		Matrix newM = new Matrix(M);
		Matrix auxM;
		Matrix zeroM = new Matrix(tamanhoMatriz);
		int i = 0;

		while(M.eq(zeroM)!=true)
		{
			auxM = new Matrix(newM);
			newM = newM.times(M);
			System.out.print("Iteracao "+i+":\n");
			newM.show();
			System.out.print("\n");
			i++;
		}

	}
}
