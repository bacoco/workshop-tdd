package modulo1.exercicio.desconto;

import java.util.ArrayList;
import java.util.List;

public class CompraBuilder {

	private List<Item> itens;

	public CompraBuilder() {
		itens = new ArrayList<Item>();
	}

	public CompraBuilder comItem(String nome, double preco) {
		Item item = new Item(nome, 1, preco);
		itens.add(item);
		return this;
	}

	public CompraBuilder comItem(String nome, int quantidade, double preco) {
		Item item = new Item(nome, quantidade, preco);
		itens.add(item);
		return this;
	}

	public Compra build() {
		return new Compra(itens);
	}

}
