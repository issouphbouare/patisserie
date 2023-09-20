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

import com.fst.patisserie.model.Achat;
import com.fst.patisserie.model.FormAchat;
import com.fst.patisserie.model.FormAchatByUser;
import com.fst.patisserie.model.FormAchatByUser;
import com.fst.patisserie.model.Users;
import com.fst.patisserie.model.Achat;
import com.fst.patisserie.repository.AchatRepository;
import com.fst.patisserie.repository.UsersRepository;

import lombok.Data;

@Data
@Service
public class AchatService implements AchatRepository {
	@Autowired
	private AchatRepository achatRepository;
	@Autowired
	private UsersRepository usersRepository;

	@Override
	public List<Achat> findAll() {
		// TODO Auto-generated method stub
		return achatRepository.findAll();
	}

	@Override
	public List<Achat> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return achatRepository.findAll(sort);
	}

	@Override
	public List<Achat> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return achatRepository.findAllById(ids);
	}

	@Override
	public <S extends Achat> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return achatRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		achatRepository.flush();

	}

	@Override
	public <S extends Achat> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return achatRepository.saveAndFlush(entity);
	}

	@Override
	public void deleteInBatch(Iterable<Achat> entities) {
		// TODO Auto-generated method stub
		achatRepository.deleteInBatch(entities);

	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		achatRepository.deleteAllInBatch();

	}

	@Override
	public Achat getOne(Integer id) {
		// TODO Auto-generated method stub
		return achatRepository.getOne(id);
	}

	@Override
	public <S extends Achat> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return achatRepository.findAll(example);
	}

	@Override
	public <S extends Achat> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return achatRepository.findAll(example, sort);
	}

	@Override
	public Page<Achat> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return achatRepository.findAll(pageable);
	}

	@Override
	public <S extends Achat> S save(S entity) {
		// TODO Auto-generated method stub
		return achatRepository.save(entity);
	}

	@Override
	public Optional<Achat> findById(Integer id) {
		// TODO Auto-generated method stub
		return achatRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return achatRepository.existsById(id);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return achatRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		achatRepository.deleteById(id);

	}

	@Override
	public void delete(Achat entity) {
		// TODO Auto-generated method stub
		achatRepository.delete(entity);

	}

	@Override
	public void deleteAll(Iterable<? extends Achat> entities) {
		// TODO Auto-generated method stub
		achatRepository.deleteAll(entities);

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		achatRepository.deleteAll();

	}

	@Override
	public <S extends Achat> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return achatRepository.findOne(example);
	}

	@Override
	public <S extends Achat> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return achatRepository.findAll(example, pageable);
	}

	@Override
	public <S extends Achat> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return achatRepository.count(example);
	}

	@Override
	public <S extends Achat> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return achatRepository.exists(example);
	}

	@Override
	public List<Achat> findByUsers(Users users) {
		// TODO Auto-generated method stub
		return achatRepository.findByUsers(users);
	}

	@Override
	public List<Achat> findByDateAchatAndUsers(Date date, Users users) {
		// TODO Auto-generated method stub
		return achatRepository.findByDateAchatAndUsers(date, users);
	}

	@Override
	public List<Achat> findByDateAchat(Date date) {
		// TODO Auto-generated method stub
		return achatRepository.findByDateAchat(date);
	}

	// Les achats par Agent

	public List<FormAchatByUser> AchatsParJourUser() {
		List<FormAchatByUser> listAchatsJour = new ArrayList();
		List<Users> listUsers = usersRepository.findAll();
		List<Achat> achats = achatRepository.findAll();

		for (Users users : listUsers) {
			for (Achat v : achats) {
				List<Achat> achatsJourUser = achatRepository.findByDateAchatAndUsers(v.getDateAchat(), users);
				FormAchatByUser achatJour = new FormAchatByUser();
				achatJour.setNombreAchat(0);
				achatJour.setMontantAchat(0);

				for (Achat achat : achatsJourUser) {
					achatJour.setNombreAchat(achatJour.getNombreAchat() + 1);
					achatJour.setMontantAchat(achatJour.getMontantAchat() + achat.getMontant());
				}
				achatJour.setUsers(users);
				achatJour.setDate(v.getDateAchat());
				boolean b = false;
				for (FormAchatByUser l : listAchatsJour) {
					if (l.getDate().equals(achatJour.getDate()) && l.getUsers().equals(achatJour.getUsers())) {
						b = true;
						break;
					}
				}
				if (b == false)
					listAchatsJour.add(achatJour);
			}
		}
		return listAchatsJour;
	}

	public List<FormAchatByUser> AchatsParMoisUser() {
		List<FormAchatByUser> listAchatsMois = new ArrayList();
		List<Users> listUsers = usersRepository.findAll();
		List<Achat> achats = achatRepository.findAll();

		for (Users users : listUsers) {
			for (Achat v : achats) {
				List<Achat> achatsMoisUser = achatRepository.findByUsers(users);
				FormAchatByUser achatMois = new FormAchatByUser();
				achatMois.setNombreAchat(0);
				achatMois.setMontantAchat(0);

				for (Achat achat : achatsMoisUser) {
					if (achat.getDateAchat().getYear() == v.getDateAchat().getYear()
							&& achat.getDateAchat().getMonth() == v.getDateAchat().getMonth()) {
						achatMois.setNombreAchat(achatMois.getNombreAchat() + 1);
						achatMois.setMontantAchat(achatMois.getMontantAchat() + achat.getMontant());
					}
				}
				achatMois.setUsers(users);
				achatMois.setDate(v.getDateAchat());
				boolean b = false;
				for (FormAchatByUser l : listAchatsMois) {
					if (l.getDate().getMonth() == achatMois.getDate().getMonth() && l.getDate().getYear() == achatMois.getDate().getYear()
							&& l.getUsers().equals(achatMois.getUsers())) {
						b = true;
						break;
					}
				}
				if (b == false)
					listAchatsMois.add(achatMois);
			}
		}
		return listAchatsMois;
	}

	public List<FormAchatByUser> AchatsParAnneesUser() {
		List<FormAchatByUser> listAchatsAnnees = new ArrayList();
		List<Users> listUsers = usersRepository.findAll();
		List<Achat> achats = achatRepository.findAll();

		for (Users users : listUsers) {
			for (Achat v : achats) {
				List<Achat> achatsMoisUser = achatRepository.findByUsers(users);
				FormAchatByUser achatAnnee = new FormAchatByUser();
				achatAnnee.setNombreAchat(0);
				achatAnnee.setMontantAchat(0);

				for (Achat achat : achatsMoisUser) {
					if (achat.getDateAchat().getYear() == v.getDateAchat().getYear()) {
						achatAnnee.setNombreAchat(achatAnnee.getNombreAchat() + 1);
						achatAnnee.setMontantAchat(achatAnnee.getMontantAchat() + achat.getMontant());
					}
				}
				achatAnnee.setUsers(users);
				achatAnnee.setDate(v.getDateAchat());
				boolean b = false;
				for (FormAchatByUser l : listAchatsAnnees) {
					if (l.getDate().getYear() == achatAnnee.getDate().getYear() && l.getUsers().equals(achatAnnee.getUsers())) {
						b = true;
						break;
					}
				}
				if (b == false)
					listAchatsAnnees.add(achatAnnee);
			}
		}
		return listAchatsAnnees;
	}

	// Les achats par AgentCourant

	public List<FormAchatByUser> AchatsParJourAgentCourant(Users users) {
		List<FormAchatByUser> listAchatsJour = new ArrayList();
		
			
				List<Achat> achatsJourUser = achatRepository.findByUsersOrderByIdDesc(users);
				for(Achat v : achatsJourUser) {
				FormAchatByUser achatJour = new FormAchatByUser();
				achatJour.setUsers(users);
				achatJour.setDate(v.getDateAchat());
				achatJour.setNombreAchat(0);
				achatJour.setMontantAchat(0);

				for (Achat achat : achatsJourUser) {
					if(v.getDateAchat().equals(achat.getDateAchat())) {
						achatJour.setNombreAchat(achatJour.getNombreAchat() + 1);
						achatJour.setMontantAchat(achatJour.getMontantAchat() + achat.getMontant());
					}
					
				}
				
				boolean b = false;
				for (FormAchatByUser l : listAchatsJour) {
					if (l.getDate().equals(achatJour.getDate()) && l.getUsers().equals(achatJour.getUsers())) {
						b = true;
						break;
					}
				}
				if (b == false)
					listAchatsJour.add(achatJour);
					
				}
				
				
		
		return listAchatsJour;
	}

	public List<FormAchatByUser> AchatsParMoisAgentCourant(Users users) {
		List<FormAchatByUser> listAchatsMois = new ArrayList();
		List<Achat> achatsMoisUser = achatRepository.findByUsersOrderByIdDesc(users);
		for (Achat v : achatsMoisUser) {
			FormAchatByUser achatMois = new FormAchatByUser();
			achatMois.setUsers(users);
			achatMois.setDate(v.getDateAchat());
			achatMois.setNombreAchat(0);
			achatMois.setMontantAchat(0);

			for (Achat achat : achatsMoisUser) {
				if (achat.getDateAchat().getYear() == v.getDateAchat().getYear()
						&& achat.getDateAchat().getMonth() == v.getDateAchat().getMonth()) {
					achatMois.setNombreAchat(achatMois.getNombreAchat() + 1);
					achatMois.setMontantAchat(achatMois.getMontantAchat() + achat.getMontant());
				}
			}
			
			boolean b = false;
			for (FormAchatByUser l : listAchatsMois) {
				if (l.getDate().getMonth() == achatMois.getDate().getMonth() && 
						l.getDate().getYear() == achatMois.getDate().getYear()) {
					b = true;
					break;
				}
			}
			if (b == false)
				listAchatsMois.add(achatMois);
		}
		return listAchatsMois;
	}

	public List<FormAchatByUser> AchatsParAnneesAgentCourant(Users users) {
		List<FormAchatByUser> listAchatsAnnees = new ArrayList();
		List<Achat> achatsAnneeUser = achatRepository.findByUsersOrderByIdDesc(users);

		for (Achat v : achatsAnneeUser) {
			
			FormAchatByUser achatAnnee = new FormAchatByUser();
			achatAnnee.setUsers(users);
			achatAnnee.setDate(v.getDateAchat());
			achatAnnee.setNombreAchat(0);
			achatAnnee.setMontantAchat(0);

			for (Achat achat : achatsAnneeUser) {
				if (achat.getDateAchat().getYear() == v.getDateAchat().getYear()) {
					achatAnnee.setNombreAchat(achatAnnee.getNombreAchat() + 1);
					achatAnnee.setMontantAchat(achatAnnee.getMontantAchat() + achat.getMontant());
				}
			}
			
			boolean b = false;
			for (FormAchatByUser l : listAchatsAnnees) {
				if (l.getDate().getYear() == achatAnnee.getDate().getYear()) {
					b = true;
					break;
				}
			}
			if (b == false)
				listAchatsAnnees.add(achatAnnee);
		}

		return listAchatsAnnees;
	}

	// Toutes les achats

	public List<FormAchat> AchatsParJour() {
		List<FormAchat> listAchatsJour = new ArrayList();
		List<Achat> achats = achatRepository.findAll();

		for (Achat v : achats) {
			List<Achat> achatsJour = achatRepository.findByDateAchat(v.getDateAchat());
			FormAchat achatJour = new FormAchat();
			achatJour.setNombreAchat(0);
			achatJour.setMontantAchat(0);

			for (Achat achat : achatsJour) {
				achatJour.setNombreAchat(achatJour.getNombreAchat() + 1);
				achatJour.setMontantAchat(achatJour.getMontantAchat() + achat.getMontant());
			}

			achatJour.setDate(v.getDateAchat());
			boolean b = false;
			for (FormAchat l : listAchatsJour) {
				if (l.getDate().equals(achatJour.getDate())) {
					b = true;
					break;
				}
			}
			if (b == false)
				listAchatsJour.add(achatJour);
		}

		return listAchatsJour;
	}

	public List<FormAchat> AchatsParMois() {
		List<FormAchat> listAchatsMois = new ArrayList();
		List<Achat> achats = achatRepository.findAll();

		for (Achat a : achats) {

			FormAchat achatMois = new FormAchat();
			achatMois.setNombreAchat(0);
			achatMois.setDate(a.getDateAchat());

			for (Achat achat : achats) {
				if (a.getDateAchat().getYear() == achat.getDateAchat().getYear()
						&& a.getDateAchat().getMonth() == achat.getDateAchat().getMonth()) {
					achatMois.setNombreAchat(achatMois.getNombreAchat() + 1);
					achatMois.setMontantAchat(achatMois.getMontantAchat() + achat.getMontant());
				}
			}
			achatMois.setDate(a.getDateAchat());
			boolean b = false;
			for (FormAchat l : listAchatsMois) {
				if (l.getDate().getMonth() == achatMois.getDate().getMonth() && l.getDate().getYear() == achatMois.getDate().getYear()) {
					b = true;
					break;
				}
			}
			if (b == false)
				listAchatsMois.add(achatMois);
		}

		return listAchatsMois;
	}

	public List<FormAchat> AchatsParAnnees() {
		List<FormAchat> listAchatsAnnees = new ArrayList();
		List<Achat> achats = achatRepository.findAll();

		for (Achat v : achats) {

			FormAchat achatAnnee = new FormAchat();
			achatAnnee.setNombreAchat(0);
			achatAnnee.setMontantAchat(0);

			for (Achat achat : achats) {
				if (v.getDateAchat().getYear() == achat.getDateAchat().getYear()) {
					achatAnnee.setNombreAchat(achatAnnee.getNombreAchat() + 1);
					achatAnnee.setMontantAchat(achatAnnee.getMontantAchat() + achat.getMontant());
				}
			}
			achatAnnee.setDate(v.getDateAchat());
			boolean b = false;
			for (FormAchat l : listAchatsAnnees) {
				if (l.getDate().getYear() == achatAnnee.getDate().getYear()) {
					b = true;
					break;
				}
			}
			if (b == false)
				listAchatsAnnees.add(achatAnnee);
		}

		return listAchatsAnnees;
	}

	//Les achats courants de Agent courant
	
	public FormAchatByUser AchatJourCourantAgent(Users users) {
		FormAchatByUser achatJour = new FormAchatByUser();
		List<Achat> achats = achatRepository.findByDateAchatAndUsers(new Date(), users);
		int n = 0;
		double m = 0;
		for (Achat achat : achats) {
			n += 1;
			m += achat.getMontant();
		}
		achatJour.setDate(new Date());
		achatJour.setNombreAchat(n);
		achatJour.setMontantAchat(m);
		achatJour.setUsers(users);
		return achatJour;
	}

	public FormAchatByUser AchatMoisCourantAgent(Users users) {
		FormAchatByUser achatMois = new FormAchatByUser();
		List<Achat> achats = achatRepository.findByUsers(users);
		int n = 0;
		double m = 0;
		for (Achat achat : achats) {
			if (achat.getDateAchat().getMonth() == (new Date()).getMonth()
					&& achat.getDateAchat().getYear() == (new Date()).getYear()) {
				n += 1;
				m += achat.getMontant();
			}
		}
		achatMois.setDate(new Date());
		achatMois.setNombreAchat(n);
		achatMois.setMontantAchat(m);
		achatMois.setUsers(users);
		return achatMois;
	}

	public FormAchatByUser AchatAnneeCourantAgent(Users users) {
		FormAchatByUser achatAnnee = new FormAchatByUser();
		List<Achat> achats = achatRepository.findByUsers(users);
		int n = 0;
		double m = 0;
		for (Achat achat : achats) {
			if (achat.getDateAchat().getYear() == (new Date()).getYear()) {
				n += 1;
				m += achat.getMontant();
			}
		}
		achatAnnee.setDate(new Date());
		achatAnnee.setNombreAchat(n);
		achatAnnee.setMontantAchat(m);
		achatAnnee.setUsers(users);
		return achatAnnee;
	}

	@Override
	public List<Achat> findByUsersOrderByIdDesc(Users users) {
		// TODO Auto-generated method stub
		return achatRepository.findByUsersOrderByIdDesc(users);
	}

	@Override
	public List<Achat> findAllByOrderByIdDesc() {
		// TODO Auto-generated method stub
		return achatRepository.findAllByOrderByIdDesc();
	}

}
