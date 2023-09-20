package com.fst.patisserie.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fst.patisserie.model.FormVenteAnnee;
import com.fst.patisserie.model.FormVenteJour;
import com.fst.patisserie.model.FormVenteMois;
import com.fst.patisserie.model.Users;
import com.fst.patisserie.model.Vente;
import com.fst.patisserie.model.VenteAnnee;
import com.fst.patisserie.model.VenteJour;
import com.fst.patisserie.model.VenteMois;
import com.fst.patisserie.repository.UsersRepository;
import com.fst.patisserie.repository.VenteRepository;

import lombok.Data;

@Data
@Service
public class VenteService implements VenteRepository {
	@Autowired
	private VenteRepository venteRepository;
	@Autowired
	private UsersRepository usersRepository;

	@Override
	public List<Vente> findAll() {
		// TODO Auto-generated method stub
		return venteRepository.findAll();
	}

	@Override
	public List<Vente> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return venteRepository.findAll(sort);
	}

	@Override
	public List<Vente> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return venteRepository.findAllById(ids);
	}

	@Override
	public <S extends Vente> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return venteRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		venteRepository.flush();

	}

	@Override
	public <S extends Vente> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return venteRepository.saveAndFlush(entity);
	}

	@Override
	public void deleteInBatch(Iterable<Vente> entities) {
		// TODO Auto-generated method stub
		venteRepository.deleteInBatch(entities);

	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		venteRepository.deleteAllInBatch();

	}

	@Override
	public Vente getOne(Integer id) {
		// TODO Auto-generated method stub
		return venteRepository.getOne(id);
	}

	@Override
	public <S extends Vente> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return venteRepository.findAll(example);
	}

	@Override
	public <S extends Vente> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return venteRepository.findAll(example, sort);
	}

	@Override
	public Page<Vente> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return venteRepository.findAll(pageable);
	}

	@Override
	public <S extends Vente> S save(S entity) {
		// TODO Auto-generated method stub
		return venteRepository.save(entity);
	}

	@Override
	public Optional<Vente> findById(Integer id) {
		// TODO Auto-generated method stub
		return venteRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return venteRepository.existsById(id);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return venteRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		venteRepository.deleteById(id);

	}

	@Override
	public void delete(Vente entity) {
		// TODO Auto-generated method stub
		venteRepository.delete(entity);

	}

	@Override
	public void deleteAll(Iterable<? extends Vente> entities) {
		// TODO Auto-generated method stub
		venteRepository.deleteAll(entities);

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		venteRepository.deleteAll();

	}

	@Override
	public <S extends Vente> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return venteRepository.findOne(example);
	}

	@Override
	public <S extends Vente> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return venteRepository.findAll(example, pageable);
	}

	@Override
	public <S extends Vente> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return venteRepository.count(example);
	}

	@Override
	public <S extends Vente> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return venteRepository.exists(example);
	}

	@Override
	public List<Vente> findByUsers(Users users) {
		// TODO Auto-generated method stub
		return venteRepository.findByUsers(users);
	}

	@Override
	public List<Vente> findByDateVenteAndUsers(Date date, Users users) {
		// TODO Auto-generated method stub
		return venteRepository.findByDateVenteAndUsers(date, users);
	}

	@Override
	public List<Vente> findByDateVente(Date date) {
		// TODO Auto-generated method stub
		return venteRepository.findByDateVente(date);
	}

	// Les ventes par Agent

	public List<FormVenteJour> VentesParJourUser() {
		List<FormVenteJour> listVentesJour = new ArrayList();
		List<Users> listUsers = usersRepository.findAll();
		List<Vente> ventes = venteRepository.findAll();

		for (Users users : listUsers) {
			for (Vente v : ventes) {
				List<Vente> ventesJourUser = venteRepository.findByDateVenteAndUsers(v.getDateVente(), users);
				FormVenteJour venteJour = new FormVenteJour();
				venteJour.setNombreVente(0);
				venteJour.setMontant(0);

				for (Vente vente : ventesJourUser) {
					venteJour.setNombreVente(venteJour.getNombreVente() + 1);
					venteJour.setMontant(venteJour.getMontant() + vente.getMontant());
				}
				venteJour.setUsers(users);
				venteJour.setDate(v.getDateVente());
				boolean b = false;
				for (FormVenteJour l : listVentesJour) {
					if (l.getDate().equals(venteJour.getDate()) && l.getUsers().equals(venteJour.getUsers())
							|| venteJour.getMontant() == 0.0) {
						b = true;
						break;
					}
				}
				if (b == false)
					listVentesJour.add(venteJour);
			}
		}
		return listVentesJour;
	}

	public List<FormVenteMois> VentesParMoisUser() {
		List<FormVenteMois> listVentesMois = new ArrayList();
		List<Users> listUsers = usersRepository.findAll();
		List<Vente> ventes = venteRepository.findAll();

		for (Users users : listUsers) {
			for (Vente v : ventes) {
				List<Vente> ventesMoisUser = venteRepository.findByUsers(users);
				FormVenteMois venteMois = new FormVenteMois();
				venteMois.setNombreVente(0);
				venteMois.setMontant(0);

				for (Vente vente : ventesMoisUser) {
					if (vente.getDateVente().getYear() == v.getDateVente().getYear()
							&& vente.getDateVente().getMonth() == v.getDateVente().getMonth()) {
						venteMois.setNombreVente(venteMois.getNombreVente() + 1);
						venteMois.setMontant(venteMois.getMontant() + vente.getMontant());
					}
				}
				venteMois.setUsers(users);
				venteMois.setMois(v.getDateVente().getMonth() + 1);
				venteMois.setAnnee(v.getDateVente().getYear() + 1900);
				venteMois.setDate(v.getDateVente());
				boolean b = false;
				for (FormVenteMois l : listVentesMois) {
					if (l.getMois() == venteMois.getMois() && l.getAnnee() == venteMois.getAnnee()
							&& l.getUsers().equals(venteMois.getUsers()) || venteMois.getMontant() == 0.0) {
						b = true;
						break;
					}
				}
				if (b == false)
					listVentesMois.add(venteMois);
			}
		}
		return listVentesMois;
	}

	public List<FormVenteAnnee> VentesParAnneesUser() {
		List<FormVenteAnnee> listVentesAnnees = new ArrayList();
		List<Users> listUsers = usersRepository.findAll();
		List<Vente> ventes = venteRepository.findAll();

		for (Users users : listUsers) {
			for (Vente v : ventes) {
				List<Vente> ventesMoisUser = venteRepository.findByUsers(users);
				FormVenteAnnee venteAnnee = new FormVenteAnnee();
				venteAnnee.setNombreVente(0);
				venteAnnee.setMontant(0);

				for (Vente vente : ventesMoisUser) {
					if (vente.getDateVente().getYear() == v.getDateVente().getYear()) {
						venteAnnee.setNombreVente(venteAnnee.getNombreVente() + 1);
						venteAnnee.setMontant(venteAnnee.getMontant() + vente.getMontant());
					}
				}
				venteAnnee.setUsers(users);
				venteAnnee.setAnnee(v.getDateVente().getYear() + 1900);
				venteAnnee.setDate(v.getDateVente());
				boolean b = false;
				for (FormVenteAnnee l : listVentesAnnees) {
					if (l.getAnnee() == venteAnnee.getAnnee() && l.getUsers().equals(venteAnnee.getUsers())
							|| venteAnnee.getMontant() == 0.0) {
						b = true;
						break;
					}
				}
				if (b == false)
					listVentesAnnees.add(venteAnnee);
			}
		}
		return listVentesAnnees;
	}
	
	// Les ventes par AgentCourant

		public List<FormVenteJour> VentesParJourAgentCourant(Users users) {
			List<FormVenteJour> listVentesJour = new ArrayList();
					List<Vente> ventesJourUser = venteRepository.findByUsersOrderByIdDesc(users);
					for(Vente v : ventesJourUser) {
					FormVenteJour venteJour = new FormVenteJour();
					venteJour.setUsers(users);
					venteJour.setDate(v.getDateVente());
					venteJour.setNombreVente(0);
					venteJour.setMontant(0);

					for (Vente vente : ventesJourUser) {
						if(v.getDateVente().equals(vente.getDateVente())) {
							venteJour.setNombreVente(venteJour.getNombreVente() + 1);
							venteJour.setMontant(venteJour.getMontant() + vente.getMontant());
						}
						
					}
					
					boolean b = false;
					for (FormVenteJour l : listVentesJour) {
						if (l.getDate().equals(venteJour.getDate())) {
							b = true;
							break;
						}
					}
					if (b == false)
						listVentesJour.add(venteJour);
						
					}
					
					
			
			return listVentesJour;
		}

		public List<FormVenteMois> VentesParMoisAgentCourant(Users  users) {
			List<FormVenteMois> listVentesMois = new ArrayList();
			List<Vente> ventesMoisUser = venteRepository.findByUsersOrderByIdDesc(users);
				for (Vente v : ventesMoisUser) {
					
					FormVenteMois venteMois = new FormVenteMois();
					venteMois.setUsers(users);
					venteMois.setDate(v.getDateVente());
					venteMois.setNombreVente(0);
					venteMois.setMontant(0);

					for (Vente vente : ventesMoisUser) {
						if (vente.getDateVente().getYear() == v.getDateVente().getYear()
								&& vente.getDateVente().getMonth() == v.getDateVente().getMonth()) {
							venteMois.setNombreVente(venteMois.getNombreVente() + 1);
							venteMois.setMontant(venteMois.getMontant() + vente.getMontant());
						}
					}
					
					boolean b = false;
					for (FormVenteMois l : listVentesMois) {
						if (l.getMois() == venteMois.getMois() && l.getAnnee() == venteMois.getAnnee()) {
							b = true;
							break;
						}
					}
					if (b == false)
						listVentesMois.add(venteMois);
				}
			return listVentesMois;
		}

		public List<FormVenteAnnee> VentesParAnneesAgentCourant(Users users) {
			List<FormVenteAnnee> listVentesAnnees = new ArrayList();
			List<Vente> ventesAnneeUser = venteRepository.findByUsersOrderByIdDesc(users);

				for (Vente v : ventesAnneeUser) {
					
					FormVenteAnnee venteAnnee = new FormVenteAnnee();
					venteAnnee.setUsers(users);
					venteAnnee.setDate(v.getDateVente());
					venteAnnee.setNombreVente(0);
					venteAnnee.setMontant(0);

					for (Vente vente : ventesAnneeUser) {
						if (vente.getDateVente().getYear() == v.getDateVente().getYear()) {
							venteAnnee.setNombreVente(venteAnnee.getNombreVente() + 1);
							venteAnnee.setMontant(venteAnnee.getMontant() + vente.getMontant());
						}
					}
					
					boolean b = false;
					for (FormVenteAnnee l : listVentesAnnees) {
						if (l.getAnnee() == venteAnnee.getAnnee()) {
							b = true;
							break;
						}
					}
					if (b == false)
						listVentesAnnees.add(venteAnnee);
				}
			
			return listVentesAnnees;
		}


	// Toutes les ventes

	public List<VenteJour> VentesParJour() {
		List<VenteJour> listVentesJour = new ArrayList();
		List<Vente> ventes = venteRepository.findAll();

		for (Vente v : ventes) {
			List<Vente> ventesJour = venteRepository.findByDateVente(v.getDateVente());
			VenteJour venteJour = new VenteJour();
			venteJour.setNombreVente(0);
			venteJour.setMontant(0);

			for (Vente vente : ventesJour) {
				venteJour.setNombreVente(venteJour.getNombreVente() + 1);
				venteJour.setMontant(venteJour.getMontant() + vente.getMontant());
			}

			venteJour.setDate(v.getDateVente());
			boolean b = false;
			for (VenteJour l : listVentesJour) {
				if (l.getDate().equals(venteJour.getDate()) || venteJour.getMontant() == 0.0) {
					b = true;
					break;
				}
			}
			if (b == false)
				listVentesJour.add(venteJour);
		}

		return listVentesJour;
	}

	public List<VenteMois> VentesParMois() {
		List<VenteMois> listVentesMois = new ArrayList();
		List<Vente> ventes = venteRepository.findAll();

		for (Vente v : ventes) {

			VenteMois venteMois = new VenteMois();
			venteMois.setNombreVente(0);
			venteMois.setMontant(0);

			for (Vente vente : ventes) {
				if (v.getDateVente().getYear() == vente.getDateVente().getYear()
						&& v.getDateVente().getMonth() == vente.getDateVente().getMonth()) {
					venteMois.setNombreVente(venteMois.getNombreVente() + 1);
					venteMois.setMontant(venteMois.getMontant() + vente.getMontant());
				}
			}
            venteMois.setDate(v.getDateVente());
			venteMois.setMois(v.getDateVente().getMonth() + 1);
			venteMois.setAnnee(v.getDateVente().getYear() + 1900);
			boolean b = false;
			for (VenteMois l : listVentesMois) {
				if (l.getMois() == venteMois.getMois() && l.getAnnee() == venteMois.getAnnee()
						|| venteMois.getMontant() == 0.0) {
					b = true;
					break;
				}
			}
			if (b == false)
				listVentesMois.add(venteMois);
		}

		return listVentesMois;
	}

	public List<VenteAnnee> VentesParAnnees() {
		List<VenteAnnee> listVentesAnnees = new ArrayList();
		List<Vente> ventes = venteRepository.findAll();

		for (Vente v : ventes) {

			VenteAnnee venteAnnee = new VenteAnnee();
			venteAnnee.setNombreVente(0);
			venteAnnee.setMontant(0);

			for (Vente vente : ventes) {
				if (v.getDateVente().getYear() == vente.getDateVente().getYear()) {
					venteAnnee.setNombreVente(venteAnnee.getNombreVente() + 1);
					venteAnnee.setMontant(venteAnnee.getMontant() + vente.getMontant());
				}
			}
            venteAnnee.setDate(v.getDateVente());
			venteAnnee.setAnnee(v.getDateVente().getYear() + 1900);
			boolean b = false;
			for (VenteAnnee l : listVentesAnnees) {
				if (l.getAnnee() == venteAnnee.getAnnee() || venteAnnee.getMontant() == 0.0) {
					b = true;
					break;
				}
			}
			if (b == false)
				listVentesAnnees.add(venteAnnee);
		}

		return listVentesAnnees;
	}

	public FormVenteJour VenteJourCourantAgent(Users users) {
		FormVenteJour venteJour = new FormVenteJour();
		List<Vente> ventes = venteRepository.findByDateVenteAndUsers(new Date(), users);
		int n = 0;
		double m = 0;
		for (Vente vente : ventes) {
				n += 1;
				m += vente.getMontant();
		}
		venteJour.setDate(new Date());
		venteJour.setNombreVente(n);
		venteJour.setMontant(m);
		venteJour.setUsers(users);
		return venteJour;
	}
	
	public FormVenteMois VenteMoisCourantAgent(Users users) {
		FormVenteMois venteMois = new FormVenteMois();
		List<Vente> ventes = venteRepository.findByUsers(users);
		int n = 0;
		double m = 0;
		for (Vente vente : ventes) {
			if (vente.getDateVente().getMonth()==(new Date()).getMonth()
					&& vente.getDateVente().getYear()==(new Date()).getYear()) {
				n += 1;
				m += vente.getMontant();
			}
		}
		venteMois.setAnnee((new Date()).getYear()+1900);
		venteMois.setMois((new Date()).getMonth()+1);
		venteMois.setNombreVente(n);
		venteMois.setMontant(m);
		venteMois.setUsers(users);
		return venteMois;
	}
	
	public FormVenteAnnee VenteAnneeCourantAgent(Users users) {
		FormVenteAnnee venteAnnee = new FormVenteAnnee();
		List<Vente> ventes = venteRepository.findByUsers(users);
		int n = 0;
		double m = 0;
		for (Vente vente : ventes) {
			if (vente.getDateVente().getYear()==(new Date()).getYear()) {
				n += 1;
				m += vente.getMontant();
			}
		}
		venteAnnee.setAnnee((new Date()).getYear()+1900);
		venteAnnee.setNombreVente(n);
		venteAnnee.setMontant(m);
		venteAnnee.setUsers(users);
		return venteAnnee;
	}

	@Override
	public List<Vente> findByUsersOrderByIdDesc(Users users) {
		// TODO Auto-generated method stub
		return venteRepository.findByUsersOrderByIdDesc(users);
	}

	@Override
	public List<Vente> findAllByOrderByIdDesc() {
		// TODO Auto-generated method stub
		return venteRepository.findAllByOrderByIdDesc();
	}

}
