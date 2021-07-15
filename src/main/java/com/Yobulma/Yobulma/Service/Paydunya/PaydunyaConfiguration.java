package com.Yobulma.Yobulma.Service.Paydunya;


import com.paydunya.neptune.PaydunyaCheckoutInvoice;
import com.paydunya.neptune.PaydunyaCheckoutStore;
import com.paydunya.neptune.PaydunyaDirectPay;
import com.paydunya.neptune.PaydunyaSetup;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Component
@Transactional
public class PaydunyaConfiguration {
   public static final String masterKey="jU1tyepI-uCmN-ylBK-JmQb-8FQvXXCP32hj";
    /*public static final String privateKey="test_private_dgYDpM7NEjeUyqeQ5g5fhKr0QnV";
    public static final String publicKey="test_public_wJ20hoHDcRAVWJxuKQplwRTPx0U";
    public static final String token="RYZ8tYghwfvD02NCkb8N";*/
    public static final String Mode="live";
    private static final double MONTANT_A_TRANSFERER = 100;
    String url;

   public static final String privateKeyprod="live_private_KE3zbpi7o8Z4gsLp7ekMiLFYG8p";
    public static final String publicKeyprod="live_public_kzt5woMiKnRwHP7nliOeSf6Itv8";
    public static final String tokenprod="aIHxm3y9qLGoedd5pp3i";


    public String paydunyaIntegration(){

            //initialisation
        PaydunyaSetup setup = new PaydunyaSetup();
        setup.setMasterKey(masterKey);
        setup.setPrivateKey(privateKeyprod);
        setup.setPublicKey(publicKeyprod);
        setup.setToken(tokenprod);
        setup.setMode(Mode); // Optionnel. Utilisez cette option pour les paiements tests.
            //description
            PaydunyaCheckoutStore store = new PaydunyaCheckoutStore();
            store.setName("Dakar Events"); // Seul le nom est requish
            store.setTagline("Yobulma la platforme qui vous permet  de trouver un livreur");
            store.setPhoneNumber("778169145");
            store.setPostalAddress("Sacré Coeur VDN derriére Good Read n7070");
           //url de  notificaton instantané à faire aprés
            /*PaydunyaCheckoutStore store = new PaydunyaCheckoutStore();
            store.setCallbackUrl("http://magasin-le-choco.com/callback_url");*/

            //paiement par redirection

/* Procédez ainsi si vous souhaitez rediriger vos clients vers notre site Web
afin qu'il puisse achever le processus de paiement.

Il est important de remarquer que le constructeur requiert respectivement comme paramètres
une instance des classes PaydunyaSetup et PaydunyaStore. */
            PaydunyaCheckoutInvoice invoice = new PaydunyaCheckoutInvoice(setup, store);
            //ajout d'information de paiements
            invoice.addItem("Abonnment A", 30, 1000, 30000);
            //invoice.addItem("Chemise Glacée", 1, 5000, 5000);
            invoice.setTotalAmount(30000);

            // Le code suivant décrit comment créer une facture de paiement au niveau de nos serveurs,
// et afficher ensuite son reçu de paiement en cas de succès.
            if (invoice.create()) {
                System.out.println(invoice.getStatus());
                System.out.println(invoice.getResponseText());
                this.url=invoice.getInvoiceUrl();
                System.out.println(invoice.getInvoiceUrl());
                return this.url;

            } else {
                System.out.println(invoice.getResponseText());
                System.out.println(invoice.getResponseCode());
            }
         //cas pour les redistribution vers d'autre compte

          /*  PaydunyaDirectPay direct_pay = new PaydunyaDirectPay(setup);

            if(direct_pay.creditAccount("EMAIL_OU_NUMERO_MOBILE_DU_CLIENT_PAYDUNYA", MONTANT_A_TRANSFERER)){
                System.out.println("Statut: " + direct_pay.getStatus());
                System.out.println("Description: " + direct_pay.getDescription());
                System.out.println("ID de la Transaction: " + direct_pay.getTransactionId());
            }else{
                System.out.println("Réponse: " + direct_pay.getResponseText());
            }*/
// Les paramètres sont l'intitulé de la taxe et le montant de la taxe.
  /*      invoice.addTax("TVA (18%)", 6300);
        invoice.addTax("Livraison", 1000);*/


        // Les données personnalisées vous permettent d'ajouter des données supplémentaires à vos informations de facture
// que pourrez récupérer plus tard à l'aide de notre action de callback confirm
      /*  invoice.addCustomData("categorie", "Jeu concours");
        invoice.addCustomData("periode", "Noël 2015");
        invoice.addCustomData("numero_gagnant", 5);
        invoice.addCustomData("prix","Bon de réduction de 50%");*/


      //restricton des moyens de paiements


        //verification de l'etat de paiement

// PayDunya rajoutera automatiquement le token de la facture sous forme de QUERYSTRING "token"
// si vous avez configuré un "return_url" ou "cancel_url".
        String invoiceToken = tokenprod;

        //PaydunyaCheckoutInvoice invoice = new PaydunyaCheckoutInvoice(setup, store);
        if (invoice.confirm(invoiceToken)) {

            // Récupérer le statut du paiement
            // Le statut du paiement peut être soit completed, pending, cancelled
            System.out.println(invoice.getStatus());
            System.out.println(invoice.getResponseText());

            // Vous pouvez récupérer le nom, l'adresse email et le
            // numéro de téléphone du client en utilisant
            // les méthodes suivantes
            System.out.println(invoice.getCustomerInfo("name"));
            System.out.println(invoice.getCustomerInfo("email"));
            System.out.println(invoice.getCustomerInfo("phone"));

            // Les méthodes qui suivent seront disponibles si et
            // seulement si le statut du paiement est égal à "completed".

            // Récupérer l'URL du reçu PDF électronique pour téléchargement
            System.out.println(invoice.getReceiptUrl());

            // Récupérer n'importe laquelle des données personnalisées que
            // vous avez eu à rajouter précédemment à la facture.
            // Merci de vous assurer à utiliser les mêmes clés que celles utilisées
            // lors de la configuration.
            System.out.println(invoice.getCustomData("Catégorie"));
            System.out.println(invoice.getCustomData("Période"));
            System.out.println(invoice.getCustomData("Gagnant N°"));
            System.out.println(invoice.getCustomData("Prix"));

            // Vous pouvez aussi récupérer le montant total spécifié précédemment
            System.out.println(invoice.getTotalAmount());

        }else{
            System.out.println(invoice.getStatus());
            System.out.println(invoice.getResponseText());
            System.out.println(invoice.getResponseCode());
        }

        return url;

    }



}
