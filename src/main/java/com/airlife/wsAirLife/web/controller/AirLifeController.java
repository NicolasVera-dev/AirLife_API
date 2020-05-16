package com.airlife.wsAirLife.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.query.AbstractJpaQuery;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.airlife.wsAirLife.dao.DatasDao;
import com.airlife.wsAirLife.dao.DatatypeDao;
import com.airlife.wsAirLife.dao.NotificationDao;
import com.airlife.wsAirLife.dao.SaveSensorDao;
import com.airlife.wsAirLife.dao.SensorDao;
import com.airlife.wsAirLife.dao.UsersDao;
import com.airlife.wsAirLife.model.Datas;
import com.airlife.wsAirLife.model.DatasByUsers;
import com.airlife.wsAirLife.model.Datatype;
import com.airlife.wsAirLife.model.Notification;
import com.airlife.wsAirLife.model.SaveSensor;
import com.airlife.wsAirLife.model.Sensor;
import com.airlife.wsAirLife.model.SensorByUsers;
import com.airlife.wsAirLife.model.Users;
import com.airlife.wsAirLife.exceptions.IntrouvableException;
import com.airlife.wsAirLife.mail.SmtpMailSender;

import io.swagger.annotations.Api;

@Api("API AirLife.")
@RestController
public class AirLifeController  {
	
    Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UsersDao usersDao;
	@Autowired
	private SensorDao sensorDao;
	@Autowired
	private DatasDao datasDao;
	@Autowired
	private NotificationDao notifDao;
	@Autowired
	private DatatypeDao datatypeDao;
	@Autowired
	private SaveSensorDao saveSensorDao;
	@Autowired
	private SmtpMailSender emailSender;
	
	@Value("${env.racine}")
	public String RACINE_WEBSITE ;

	
	@GetMapping(value="/users")
	public List<Users> recupererUsers() {
		logger.info("Affichage de tous les utilisateurs...");
		return usersDao.findAll();
	}
	
	@GetMapping(value="/users/{id}")
	public Users recupererUsersParId(@PathVariable int id) {
		logger.info("Affichage d'un utilisateur en fonction d'un iduser...");
		Users users = usersDao.findById(id);
		if(users == null)
			throw new IntrouvableException("L'utilisateur avec l'id " +id+ " n'existe pas");
	
		return users;
	}
	
	@PostMapping(value="/inscription")
	@ResponseBody
	public Map<String,Boolean> ajouterUsers(@RequestBody Users users) throws MessagingException{
		logger.info("Ajout d'un utilisateur..."); 
		Users userAdded = usersDao.save(users);
		if(userAdded == null) 
			return Collections.singletonMap("success", false);
			 		 
		else {
				
			emailSender.send(userAdded.getEmail(),
					"Airlife - Confirmation d'inscription",
					"Bonjour " + userAdded.getFirstname() + " " + userAdded.getLastname()
    				+ ", veuillez cliquer sur le lien suivant afin de confirmer votre adresse mail :"
    				+ "<form method=\"post\" action=\""+RACINE_WEBSITE+userAdded.getConfirmation_token()+"/activation\">"
    				+ "<input type=\"hidden\" name=\"_method\" value=\"PUT\">"
    				+ "<button type=\"submit\"> confirmer votre email </a>"
    				+ "</form>");
		}	
				
		return Collections.singletonMap("success", true);
	}
	
	@PutMapping(value="{confirmation_token}/activation")
	@ResponseBody
	public String activationUser(@PathVariable String confirmation_token) {
		logger.info("Activation du compte d'un utilisateur...");
		Users userValid = usersDao.findByConfirmationToken(confirmation_token);
		if(userValid == null) {
			throw new IntrouvableException("L'utilisateur avec le token " +confirmation_token+ " n'existe pas");
		} else {
			userValid.setConfirmation_token(null);
			usersDao.save(userValid);
			return "Votre compte a bien été activé";
		}
	}
	
	@PutMapping(value = "/users")
    public void updateData(@RequestBody Users user) {
		logger.info("Modification d'un utilisateur...");
        usersDao.save(user);
    }
	
	@PutMapping(value = "/users/{id}/{mdp}")
	public void updatePassword(@PathVariable Integer id, @PathVariable String mdp) {
		logger.info("Modification du mot de passe d'un utilisateur en fonction de son id...");
		Users users = recupererUsersParId(id);
		users.setPassword(mdp);
		usersDao.save(users);
    }
	
	@GetMapping(value="/sensors")
	public List<Sensor> recupererSensors() {
		logger.info("Récupération des capteurs...");
		return sensorDao.findAll();
	}
	
	@GetMapping(value="/sensor/{id}")
	public Sensor recupererSensorParId(@PathVariable int id) {
		logger.info("Récupération d'un capteur en fonction de son id...");
		Sensor sensor = sensorDao.findById(id); 
		if(sensor == null) 
			throw new IntrouvableException("Le capteur avec l'id " +id+ " n'existe pas");
	
		return sensor;
	}
	
	@GetMapping(value="/datatype/{id}")
	public Datatype recupererDatatypeParId(@PathVariable int id) {
		logger.info("Récupération d'une datatype en fonction de son id...");
		Datatype datatype = datatypeDao.findById(id); 
		if(datatype == null) 
			throw new IntrouvableException("La datatype avec l'id " +id+ " n'existe pas");
	
		return datatype;
	}
	
	
	@GetMapping(value="/savesensor")
	public List<SaveSensor> recupererAllSaveSensor() {
		logger.info("Récupération des savesensor...");
		return saveSensorDao.findAll();
	}
	
	@PostMapping(value="/savesensor")
	public String ajouterSaveSensor(@RequestParam int idsensor, @RequestParam String nameSensor) {
		logger.info("Ajout d'un savesensor...");
		
		SaveSensor saveSensor = new SaveSensor(idsensor,nameSensor);
		saveSensorDao.save(saveSensor);		
		
		return "Le saveSensor a bien été ajouté";
	}
	
	@GetMapping(value="/savesensor/{id}")
	public SaveSensor recupererSaveSensorParId(@PathVariable int id) {
		logger.info("Récupération d'un savesensor en fonction de son id...");
		SaveSensor savesensor = saveSensorDao.findSensorById(id); 
		if(savesensor == null) 
			throw new IntrouvableException("Le savesensor avec l'id " +id+ " n'existe pas");
	
		return savesensor;
	}
	
	
	@PostMapping(value="/sensors")
	@ResponseBody
	public Map<String, Boolean> ajouterSensor(@RequestParam int idsensor, @RequestParam String username){
		logger.info("Ajout d'un capteur...");
		//On récupère le current Timestamp
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		long currentTimestamp = timestamp.getTime();

		//On initialise le user lié au capteur
		Sensor sensor = new Sensor();
		
		Users user = null;

		if(usersDao.findByEmail(username) != null) {
			user = usersDao.findByEmail(username);
		}
		else if(usersDao.findByLogin(username) != null) {
			user = usersDao.findByLogin(username);
		}
		
				
		if(user == null) 
			throw new IntrouvableException("L'utilisateur avec le username " +username+ " n'existe pas");
		
		sensor.setUser(user);
		sensor.setIdsensor(idsensor);
		
		//1. Vérification de la présence de l'idsensor dans la table save_sensor
		SaveSensor savesensor = saveSensorDao.findSensorById(idsensor);
		
		if(savesensor == null) 
			throw new IntrouvableException("Le savesensor avec l'id " +idsensor+ " n'existe pas");
		
		if(savesensor!=null) {
			
			//2. On vérifie que nous sommes bien à moins de 5mn
			long date_heure_ajout = savesensor.getDate_heure_ajout().getTime();			

			long diff = currentTimestamp - date_heure_ajout;
			long diff_minutes = diff / (60 * 1000);
			
			if(diff_minutes <= 5 && diff_minutes >= 0) {
				
				//3. On récupère le namesensor dans la table save_sensor
				sensor.setText(savesensor.getName_sensor());
				//4. On créé le sensor
				sensorDao.save(sensor);
				logger.info("Le capteur a bien été ajouté");
				//5. On ajoute la notification
				logger.info("Ajout de la notification");
				Notification notif = new Notification(user, "Le capteur "+ sensor.getText() +" a bien été ajouté", timestamp);
				notifDao.save(notif);

				return Collections.singletonMap("success", true);
			} 
			else {
				logger.info("Le capteur n'a pas été ajouté, le délai de 5 minutes à été écoulé");
				logger.info("Le délai d'erreur est de " + diff_minutes + " minutes");
				return Collections.singletonMap("success", false);
			}
		}
		else {
			logger.info("Le capteur avec l'id " +idsensor+ "n'existe pas");
			return Collections.singletonMap("success", false);
		}
	}
	
	@DeleteMapping(value="/deleteSensor")
	@ResponseBody
	public Map<String, Boolean> deleteSensor(@RequestParam int idsensor) {
		logger.info("Suppression d'un capteur...");
		Sensor sensor = sensorDao.findById(idsensor);
		if(sensor==null)
			throw new IntrouvableException("Le capteur avec l'id " +idsensor+ " n'existe pas");
		sensorDao.delete(sensor);
		return Collections.singletonMap("success", true);
	}
	
	@GetMapping(value="/datas")
	public List<Datas> recupererDatas(){
		logger.info("Récupération de toutes les datas...");
		return datasDao.findAll();
	}
	
	@PostMapping(value="/datasByUsers/")
	public List<DatasByUsers> getDatasByUsers(@RequestParam String username){
		logger.info("Récupération des datas en fonction d'un utilisateur...");
		if(usersDao.findByEmail(username) != null)
			return datasDao.findDataByUserEmail(username);
		else if(usersDao.findByLogin(username) != null)
			return datasDao.findDataByUserLogin(username);
		else 
			return null;
	}
	
	@PostMapping(value="/sensorsByUsers/")
	public List<SensorByUsers> getSensorsByUsers(@RequestParam String username){
		logger.info("Récupération des capteurs d'un utilisateur...");
		if(usersDao.findByEmail(username) != null)
			return sensorDao.findSensorByUserEmail(username);
		else if(usersDao.findByLogin(username) != null)
			return sensorDao.findSensorByUserLogin(username);
		else 
			return null;
	}
	
	@GetMapping(value="/datas/{id}")
	public Datas recupererDataParId(@PathVariable int id){
		logger.info("Récupération d'une data en fonction de son id...");
		Datas data = datasDao.findById(id); 
		if(data == null) 
			throw new IntrouvableException("La donnée"
					+ "e avec l'id " +id+ " n'existe pas");
	
		return data;
	}
	
	@PostMapping(value="/datas")
	@ResponseBody
	public String ajouterData(@RequestParam int idsensor, @RequestParam int iddatatype,@RequestParam double datavalue){
		logger.info("Ajout d'une donnée...");  
		
		//On récupère le current Timestamp
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		Sensor sensor = recupererSensorParId(idsensor);
		if(sensor == null)
			throw new IntrouvableException("Le capteur avec l'id " +idsensor+ " n'existe pas");
		Datatype datatype = recupererDatatypeParId(iddatatype);
		if(datatype == null)
			throw new IntrouvableException("La datatype avec l'id " +iddatatype+ " n'existe pas");
		Datas data = new Datas(sensor, datatype, datavalue);
		
		//On récupère le iduser en fonction de l'idsensor
		int iduser = sensorDao.findUserBySensor(idsensor);
		//On récupère le user en fonction de son id
		Users user = usersDao.findById(iduser);
		//On récupère le PlayerID
		String playerID = user.getPlayer_id_navigateurs();
		
		//Si datavalue > seuil
		if(datavalue>0) {
		//Alors on envoie la notification
			//On joue le script avec le PlayerID
			sendNotif(playerID);
			//On enregistre la notification dans la table notifications
			logger.info("Ajout de la notification..."); 
			Notification notif = new Notification(user, "Le seuil a été dépassé", timestamp);
			notifDao.save(notif);
		}
		datasDao.save(data);
		return "La donnée a bien été ajoutée";
	}
	
	@GetMapping(value="/notifications")
	public List<Notification> recupererNotification() {
		logger.info("Récupération des notifications...");
		return notifDao.findAll();
	}
	
	@GetMapping(value="/notification/{id}")
	public Notification recupererNotificationParId(@PathVariable int id){
		logger.info("Récupération d'une notification en fonction de son id...");
		Notification notif = notifDao.findById(id);
		if(notif == null)
			throw new IntrouvableException("La notification avec l'id " +id+ " n'existe pas");
		return notif;
	}
	
	@PostMapping(value="/login")
	@ResponseBody
	public Map<String, Boolean> login(@RequestParam String username, @RequestParam String password) {
		logger.info("Connexion d'un utilisateur...");
		// Si l'utilisateur a saisi un email
		// On vérifie que cet email existe
		if(usersDao.findByEmail(username) != null) 
		{
			// On recupère le mot de passe de l'utilisateur en fonction de l'adresse mail saisie
			// remplacement des deux premiers caractères du mot de passe pour la correspondance Bcrypt Java / Php
			String password_db = usersDao.findByEmail(username).getPassword().replaceFirst("2y", "2a");
			// Si le mot de passe saisi par l'utilisateur concorde avec ce qu'il y a en base => succès, sinon => echec
			if (BCrypt.checkpw(password, password_db))
				return Collections.singletonMap("success", true);
			else
				return Collections.singletonMap("success", false);
		} 
		// Si l'utilisateur a saisi un login
		// On vérifie que ce login existe
		else if(usersDao.findByLogin(username) != null)
		{ 
			// On recupère le mot de passe de l'utilisateur en fonction du login saisi
			// remplacement des deux premiers caractères du mot de passe pour la correspondance Bcrypt Java / Php
			String password_db = usersDao.findByLogin(username).getPassword().replaceFirst("2y", "2a");
			// Si le mot de passe saisi par l'utilisateur concorde avec ce qu'il y a en base => succès, sinon => echec
			if (BCrypt.checkpw(password, password_db))
				return Collections.singletonMap("success", true);
			else
				return Collections.singletonMap("success", false);
		} 
		else
			return Collections.singletonMap("success", false);
	}
	
	@PostMapping(value = "/forgotPassword")
	@ResponseBody
	public Map<String, Boolean> forgotPassword(@RequestParam String email) throws MessagingException {
		logger.info("Mot de passe oublié...");
		Users user = usersDao.findByEmail(email);
		//1. On vérifie que le mail existe
		if(user!=null) {
			//2. On envoie un mail de confirmation avec token à ce mail
			emailSender.send(user.getEmail(),
					"Airlife - Changement de mot de passe",
					"Bonjour " + user.getFirstname() + " " + user.getLastname()
    				+ ", veuillez cliquer sur le lien suivant afin de confirmer votre souhait de changer de mot de passe :"
    				+ "<form method=\"post\" action=\""+RACINE_WEBSITE+"changePassword/"+user.getIduser()+"/"+user.generateToken()+"\">"
    				+ "<input type=\"hidden\" name=\"_method\" value=\"PUT\">"
    				+ "<button type=\"submit\"> Changer de mot de passe </a>"
    				+ "</form>");
		}else 
			return Collections.singletonMap("success", false);
		
		return Collections.singletonMap("success", true);
	}
	
	@PutMapping(value = "/changePassword/{id}/{token}")
	@ResponseBody
	public Map<String, Boolean> changePassword(@PathVariable int id, @PathVariable String token) throws MessagingException {
		logger.info("Mot de passe oublié...");
		Users user = usersDao.findById(id);
		
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[8];
		random.nextBytes(bytes);
		String password = bytes.toString();
		password = password.substring(3);
		user.setPassword(password);
		
		if(user==null)
			return Collections.singletonMap("success", false);
		else {
			emailSender.send(user.getEmail(),
					"Airlife - Nouveau mot de passe",
					"Bonjour " + user.getFirstname() + " " + user.getLastname()
    				+ ", Votre nouveau mot de passe est :" + password
    		);
			usersDao.save(user);
		}
		return Collections.singletonMap("success", true);
	}
	
	public static void sendNotif(String PlayerID) {
        try {
        	URL test = new URL("http://airlife.nicolas-a.fr/cron_send_notification.php");
        	URLConnection connexion = test.openConnection();
        	connexion.setDoOutput(true);
        	PrintStream ps = new PrintStream(connexion.getOutputStream());
        	ps.print("&PlayerID=" + PlayerID);
        	connexion.getInputStream();
        	ps.close();
        
        	BufferedReader in = new BufferedReader(
                new InputStreamReader(
                		connexion.getInputStream()));
        	String inputLine;
        	while ((inputLine = in.readLine()) != null) 
        		System.out.println(inputLine);
        	in.close();
        
	    } catch (MalformedURLException e1) {
	    	e1.printStackTrace();
	    } catch (IOException e2) {
	    	e2.printStackTrace();
	    }
	}
	
	/*@GetMapping(value="/test")
	public static void testNotif() {
        String PlayerID="e873c6e5-4922-45e7-a984-dcfc682b9c08";
        try {
		// fonction appelé lorsqu'une donnée a été ajoutée avec un seuil dépassé.
		// le but est d'appelé le script en modifiant la valeur de ID avec celle récupérée en base
		// il faut donc faire un SELECT player_id pour récupérer cette valeur
		URL test = new URL("http://airlife.nicolas-a.fr/cron_send_notification.php");
		URLConnection testco = test.openConnection();
		testco.setDoOutput(true);
		PrintStream ps = new PrintStream(testco.getOutputStream());
        ps.print("&PlayerID=" + PlayerID);
        testco.getInputStream();
        ps.close();
        
		BufferedReader in = new BufferedReader(
                new InputStreamReader(
                testco.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) 
            System.out.println(inputLine);
        in.close();
        
	    } catch (MalformedURLException e1) {
	        e1.printStackTrace();
	    } catch (IOException e2) {
	        e2.printStackTrace();
	    }
        //b0b15de8-0982-4a93-afc8-ad4c7c0e109f
	}*/
	
	
}
