package modulo2.exercicio.mocks;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class FiltroDeFaturasTest {
	
	FiltroDeFaturas filtroDeFaturas;
	RepositorioDeFaturas mock;
	private ArrayList<Fatura> faturas;

	@Before
	public void setUp() throws Exception {		
		mock = Mockito.mock(RepositorioDeFaturas.class);

		filtroDeFaturas = new FiltroDeFaturas(mock);
		faturas = new ArrayList<Fatura>();
		Mockito.when(mock.todas()).thenReturn(faturas);

	}

	@Test
	public void filtraMaioresQue2000() {
		faturas.add(new Fatura(Calendar.getInstance(), "João", 500));
		faturas.add(new Fatura(Calendar.getInstance(), "Mané", 3000));
		
		List<Fatura> filtrada = filtroDeFaturas.filtra();
		
		assertEquals(1, filtrada.size());
	}
	
	@Test
	public void filtraFaturaAnteriorA1999ComValor2000(){
		faturas.add(new Fatura(new GregorianCalendar(1998,Calendar.APRIL,1), "Xuxa", 2000));
		faturas.add(new Fatura(Calendar.getInstance(), "zé", 1));
		
		List<Fatura> filtrada = filtroDeFaturas.filtra();
		
		assertFatura(filtrada, 2000);
	}
	
	private void assertFatura(List<Fatura> lista, double... valores) {
		
		assertEquals(lista.size(), valores.length);
		for(int i = 0; i < valores.length; i++) {
			assertEquals(lista.get(0).getValor(), valores[i], 0.0001);
		}
	}

	@Test
	public void filtraFaturaSeForDaMicro$$oft(){
		faturas.add(new Fatura(Calendar.getInstance(), "MICROSOFT", 500));
		faturas.add(new Fatura(Calendar.getInstance(), "Mané", 300));
		
		List<Fatura> filtrada = filtroDeFaturas.filtra();
		
		Assert.assertEquals(1, filtrada.size());
	}
	
	@Test
	public void filtraFaturasAnterioresA1999(){
		
		Calendar dataAntiga = new GregorianCalendar(1998,Calendar.JULY,1);
		
		faturas.add(new Fatura(dataAntiga, "João", 500));
		faturas.add(new Fatura(Calendar.getInstance(), "Mané", 200));
		
		List<Fatura> filtrada = filtroDeFaturas.filtra();
		
		Assert.assertEquals(1, filtrada.size());
		Assert.assertEquals(500, filtrada.get(0).getValor(), 0.0001);
	}

}
