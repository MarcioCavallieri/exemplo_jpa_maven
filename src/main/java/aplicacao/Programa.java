package aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import dominio.Pessoa;

public class Programa {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aula_jpa");
		EntityManager em = emf.createEntityManager();
		boolean inserir;
		boolean editar;
		boolean deletar;

		Pessoa p1 = new Pessoa(null, "Marcio", "marcio@marcio.com");
		Pessoa p2 = new Pessoa(null, "Andre", "andre@andre.com");
		Pessoa p3 = new Pessoa(null, "Joao", "joao@joao.com");
		inserir = false;
		editar = true;
		deletar = false;

		try {
			em.getTransaction().begin();

			if (inserir) {
				em.persist(p1);
				em.persist(p2);
				em.persist(p3);
				System.out.println("Inseriu!");
			}

			if (editar) {
				Pessoa p = em.find(Pessoa.class, 3);
				p.setEmail("joaozinho@joaozino");
				System.out.println("Atualizou!");
			}

			if (deletar) {
				em.remove(p1);
				em.remove(p2);
				em.remove(p3);
				System.out.println("Deletou!");
			}

			em.getTransaction().commit();
		} finally {
			if (inserir)
				System.out.println("Inseriu!");
			else 
				if (editar)
					System.out.println("Atualizou!");
				else 
					if (deletar)
						System.out.println("Deletou!");

			em.close();
			emf.close();
		}
	}
}