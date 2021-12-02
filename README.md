# VAT Kata

## How to use

### Main application

#### In your IDE

Launch method `main()` in the `VatKata` class.

#### "For real"

1. Build the application : `mvn package`
2. Execute the generated JAR in `target` : `java -jar target/vat-kata-0.0.1-SNAPSHOT.jar`

### Test

#### In your IDE

Launch `fr.alainncls.vatkata.service.CartComputeServiceTest`

#### "For real"

Via Maven : `mvn test`

## Énoncé

Aucune taxe n'est appliquée sur les produits de première nécessité, à savoir la nourriture et les médicaments.
Une taxe sur la valeur ajoutée de 10% est appliquée sur les livres.
Une taxe sur la valeur ajoutée de 20% est appliquée sur tous les autres produits.

Une taxe additionnelle de 5% est appliquée sur les produits importés, sans exception.

Le montant de chacune des taxes est arrondi aux 5 centimes supérieurs, selon la règle suivante :

| Taxe calculée | Taxe imputée |
|---------------|--------------|
|          0.99 |         1.00 |
|          1.00 |         1.00 |
|          1.01 |         1.05 |
|          1.02 |         1.05 |

Lorsque l'on passe une commande, une facture est émise listant chacun des produits ainsi que leurs
prix TTC ; au bas de la facture figurent le montant total (TTC) ainsi que le montant total des taxes.
Le montant TTC est calculé comme suit :
Pttc = Pht + somme(arrondi(Pht*t/100))
Pttc: Prix TTC
Pht : Prix hors taxes
t : taxe applicable

Ecrire une application qui imprime la facture détaillée pour chacun des paniers suivants :
(pas besoin d'affichage sophistiqué, un (très) simple affichage en console suffira)

### INPUT

#### Input 1

* 2 livres à 12.49€
* 1 CD musical à 14.99€
* 3 barres de chocolat à 0.85€

#### Input 2

* 2 boîtes de chocolats importée à 10€
* 3 flacons de parfum importé à 47.50€

#### Input 3

* 2 flacons de parfum importé à 27.99€
* 1 flacon de parfum à 18.99€
* 3 boîtes de pilules contre la migraine à 9.75€
* 2 boîtes de chocolats importés à 11.25€

### OUTPUT

#### Output 1

* 2 livres à 12.49€ : 27,5€ TTC
* 1 CD musical à 14.99€ : 18€ TTC
* 3 barres de chocolat à 0.85€ : 2.55€ TTC

Montant des taxes : 5.53€
Total : 48.05€

#### Output 2

* 2 boîtes de chocolats importée à 10€ : 21€
* 3 flacons de parfum importé à 47.50€ : 178,15€

Montant des taxes : 36.65€
Total : 199.15€

#### Output 3

* 2 flacons de parfum importé à 27.99€ : 70€
* 1 flacon de parfum à 18.99€ : 22.8€
* 3 boîtes de pilules contre la migraine à 9.75€ : 29.25€
* 2 boîtes de chocolats importés à 11.25€ : 23.65€

Montant des taxes : 18.98
Total : 145,7
