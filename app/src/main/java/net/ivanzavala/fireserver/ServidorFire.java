package net.ivanzavala.fireserver;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import java.io.FileInputStream;

public class ServidorFire {
    public static void main(String[] args) {

        try {
            FileInputStream serviceAccount =
                    new FileInputStream("C:/Users/zaval/Documents/1 - OCTAVO SEMESTRE/PRO.MOVIL/CLAVE PRIVADA FIREBASE APP/fir-cloudmessenging-4a58a-firebase-adminsdk-dr74t-c442f7d988.json");

            //$env:GOOGLE_APPLICATION_CREDENTIALS="C:/Users/zaval/Documents/1 - OCTAVO SEMESTRE/PRO.MOVIL/CLAVE PRIVADA FIREBASE APP/fir-cloudmessenging-4a58a-firebase-adminsdk-dr74t-c442f7d988.json"

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp defaultApp = FirebaseApp.initializeApp(options);
            System.out.println(defaultApp.getName());

            // This registration token comes from the client FCM SDKs.
            String registrationToken = "da_OBwgGRgWeaHAsDpmNJ7:APA91bFUg0HWZ_OjTNlQ-IXWM0ya1Hshr36ahbGtS4WpGW1JXreBezsiI7mQRNocN1drymjuLgCEyZP52qGWjDnIP0ShcqDalM1RGLKmam8G0ffOvNSP_AuP5dOZGeaOvSHs4dYdzBi1";

              // See documentation on defining a message payload.
            Message message = Message.builder()
                    .setNotification(Notification.builder()
                            .setTitle("Alerta")
                            .setBody("Te han depositado $50,000 a tu cuenta")
                            .build())
                    .putData("score", "850")
                    .putData("time", "2:45")
                    .setToken(registrationToken)
                    .build();

               // Send a message to the device corresponding to the provided
                     // registration token.
            String response = FirebaseMessaging.getInstance().send(message);
                     // Response is a message ID string.
            System.out.println("Successfully sent message: " + response);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
}
