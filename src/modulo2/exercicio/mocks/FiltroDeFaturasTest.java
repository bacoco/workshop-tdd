package modulo2.exercicio.mocks;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class FiltroDeFaturasTest {
	
	FiltroDeFaturas filtroDeFaturas;
	RepositorioDeFaturas mock;

	@Before
	public void setUp() throws Exception {		
		mock = Mockito.mock(RepositorioDeFaturas.class);

		filtroDeFaturas = new FiltroDeFaturas(mock);
	}

	@Test
	public void filtraMaioresQue2000() {
		List<Fatura> faturas = new ArrayList<Fatura>();
		faturas.add(new Fatura(Calendar.getInstance(), "João", 500));
		faturas.add(new Fatura(Calendar.getInstance(), "Mané", 3000));
		
		Mockito.when(mock.todas()).thenReturn(faturas);
		
		List<Fatura> filtrada = filtroDeFaturas.filtra();
		
		Assert.assertEquals(1, filtrada.size());
		
	}
	
	@Test
	public void filtraFaturaDaMicro$$oftMaiorQue2000(){
		List<Fatura> faturas = new ArrayList<Fatura>();
		faturas.add(new Fatura(Calendar.getInstance(), "MICROSOFT", 2000));
		faturas.add(new Fatura(Calendar.getInstance(), "Mané", 300));
		
		Mockito.when(mock.todas()).thenReturn(faturas);
				
		List<Fatura> filtrada = filtroDeFaturas.filtra();
		
		Assert.assertEquals(1, filtrada.size());
	}
	
	@Test
	public void filtraFaturaSeForDaMicro$$oft(){
		List<Fatura> faturas = new ArrayList<Fatura>();
		faturas.add(new Fatura(Calendar.getInstance(), "MICROSOFT", 500));
		faturas.add(new Fatura(Calendar.getInstance(), "Mané", 300));
		
		Mockito.when(mock.todas()).thenReturn(faturas);
				
		List<Fatura> filtrada = filtroDeFaturas.filtra();
		
		Assert.assertEquals(1, filtrada.size());
	}
	
	@Test
	public void filtraFaturasAnterioresA1999(){
		List<Fatura> faturas = new ArrayList<Fatura>();
		
		Calendar dataAntiga = new GregorianCalendar(1998,Calendar.JULY,1);
		
		faturas.add(new Fatura(dataAntiga, "João", 500));
		faturas.add(new Fatura(Calendar.getInstance(), "Mané", 200));
		
		Mockito.when(mock.todas()).thenReturn(faturas);
				
		List<Fatura> filtrada = filtroDeFaturas.filtra();
		
		Assert.assertEquals(1, filtrada.size());
	}

}
