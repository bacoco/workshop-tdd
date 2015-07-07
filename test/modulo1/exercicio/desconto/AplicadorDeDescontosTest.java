package modulo1.exercicio.desconto;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AplicadorDeDescontosTest {

	AplicadorDeDescontos descontos;
	private static final double DELTA = 0.00001;
	
	@Before
	public void setUp() {
		descontos = new AplicadorDeDescontos();
	}

	@Test
	public void macbookEIphoneTemDeconto(){
		Compra compra = new CompraBuilder().comItem("IPHONE", 1500).comItem("MACBOOK", 3000).build();
		
		descontos.aplica(compra);
				
		Assert.assertEquals((3000+1500) * 0.85, compra.getValorLiquido(), DELTA);
	}
	
	@Test
	public void notebookEWindowsPhoneTemDesconto(){
		Compra compra = new CompraBuilder().comItem("WINDOWS PHONE", 1300).comItem("NOTEBOOK", 2500).build();
		
		descontos.aplica(compra);
		
		Assert.assertEquals((2500+1300) * 0.88, compra.getValorLiquido(), DELTA);
	}
	
	@Test
	public void xboxTemDesconto(){
		Compra compra = new CompraBuilder().comItem("XBOX", 2500).build(); 
		
		descontos.aplica(compra);
		
		Assert.assertEquals(2500 * 0.3, compra.getValorLiquido(), DELTA);
	}
	
	@Test
	public void doisItensMenosQueMilTemDesconto(){
		Compra compra = new CompraBuilder().comItem("ABAJUR", 2, 100).build();
		
		descontos.aplica(compra);
		
		Assert.assertEquals(200 * 0.98, compra.getValorLiquido(), DELTA);
		
	}
	
	@Test
	public void tresOu4ItensEValorMaiorQue5MilTemDesconto(){
		Compra compra = new CompraBuilder().comItem("MONITOR", 3, 1100).build();		
		
		descontos.aplica(compra);
		
		Assert.assertEquals((1100 * 3) * 0.95, compra.getValorLiquido(), DELTA);
		
		compra = new CompraBuilder().comItem("MONITOR", 3, 1100).comItem("TRECO", 100).build();
		descontos.aplica(compra);
		
		Assert.assertEquals(3400 * 0.95, compra.getValorLiquido(), DELTA);
	}
		
	@Test
	public void maisQue5ItensEValorMaiorQue3MilTemDesconto(){
		Compra compra = new CompraBuilder().comItem("COISA", 6, 1000).build();
		
		descontos.aplica(compra);
		
		Assert.assertEquals((1000 * 6) * 0.94, compra.getValorLiquido(), DELTA);
	}

}
