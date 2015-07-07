package modulo1.exercicio.desconto;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AplicadorDeDescontosTest {

	AplicadorDeDescontos descontos;
	List<Item> itens;
	private static final double DELTA = 0.00001;
	
	@Before
	public void setUp() {
		descontos = new AplicadorDeDescontos();
		itens = new ArrayList<Item>();
	}

	@Test
	public void macbookEIphoneTemDeconto(){
		itens.add(new Item("IPHONE", 1, 1500));
		itens.add(new Item("MACBOOK", 1, 3000));
		Compra compra = new Compra(itens);
		
		descontos.aplica(compra);
				
		Assert.assertEquals((3000+1500) * 0.85, compra.getValorLiquido(), DELTA);
	}
	
	@Test
	public void notebookEWindowsPhoneTemDesconto(){
		itens.add(new Item("WINDOWS PHONE", 1, 1300));
		itens.add(new Item("NOTEBOOK", 1, 2500));
		Compra compra = new Compra(itens);
		
		descontos.aplica(compra);
		
		Assert.assertEquals((2500+1300) * 0.88, compra.getValorLiquido(), DELTA);
	}
	
	@Test
	public void xboxTemDesconto(){
		itens.add(new Item("XBOX", 1, 2500));
		Compra compra = new Compra(itens);
		
		descontos.aplica(compra);
		
		Assert.assertEquals(2500 * 0.3, compra.getValorLiquido(), DELTA);
	}
	
	@Test
	public void doisItensMenosQueMilTemDesconto(){
		itens.add(new Item("ABAJUR", 2, 100));
		Compra compra = new Compra(itens);
		
		descontos.aplica(compra);
		
		Assert.assertEquals(200 * 0.98, compra.getValorLiquido(), DELTA);
		
	}
	
	@Test
	public void tresOu4ItensEValorMaiorQue5MilTemDesconto(){
		itens.add(new Item("MONITOR", 3, 1100));
		Compra compra = new Compra(itens);
		
		descontos.aplica(compra);
		
		Assert.assertEquals((1100 * 3) * 0.95, compra.getValorLiquido(), DELTA);
		
		itens.add(new Item("TRECO", 1, 100));
		Compra compra2 = new Compra(itens);
		descontos.aplica(compra2);
		
		Assert.assertEquals(3400 * 0.95, compra2.getValorLiquido(), DELTA);
	}
	
	@Test
	public void maisQue5ItensEValorMaiorQue3MilTemDesconto(){
		itens.add(new Item("COISA", 6, 1000));
		Compra compra = new Compra(itens);
		
		descontos.aplica(compra);
		
		Assert.assertEquals((1000 * 6) * 0.94, compra.getValorLiquido(), DELTA);
	}

}
