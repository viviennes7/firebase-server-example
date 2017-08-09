package com.spring.test;

import java.io.FileInputStream;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

@Repository
public class FirebaseDao {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private static final String authPath = "C:/Users/ylland_IT2/Desktop/김민수/FCM/test-fcb30-firebase-adminsdk-4b0y4-fe0c557599.json";
	private static final String databaseUrl = "https://test-fcb30.firebaseio.com/"; 
	private DatabaseReference ref; 
	
	@PostConstruct
	public void init() throws Exception {
		logger.info("###########FirebaseDao.init() ###########");
		
		FileInputStream serviceAccount = new FileInputStream(authPath);

		FirebaseOptions options = new FirebaseOptions.Builder()
			.setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
			.setDatabaseUrl(databaseUrl)
			.build();

		FirebaseApp.initializeApp(options);

		FirebaseDatabase database = FirebaseDatabase.getInstance();
		ref = database.getReference();

		ref.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				Object document = dataSnapshot.getValue();
				logger.info("Data Change ::::: {}", document);
			}

			@Override
			public void onCancelled(DatabaseError arg0) {
				String errMsg = arg0.getMessage();
				logger.info("Data Cancel ::::: {}", errMsg);
			}
		});
	}
	
	public void save(Object obj, String child) throws Exception{
		DatabaseReference childRef = ref.child(child);
		
		childRef.push().setValue(obj);
	}
	
	public <T> T findOne(String id, String child){
		DatabaseReference childRef = ref.child(child);
		final Object result = null;
		
		childRef.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				Object document = dataSnapshot.getValue();
//				result = document;
				logger.info("Data Change ::::: {}", document);
			}

			@Override
			public void onCancelled(DatabaseError arg0) {
				String errMage = arg0.getMessage();
				logger.error("Data Cancel ::::: {}", errMage);
			}
		});
		return null;
	}
	
	public void update(Map<String, Object> updates, String child){
		DatabaseReference childRef = ref.child(child);
		
		
		childRef.updateChildren(updates);
	}
}
