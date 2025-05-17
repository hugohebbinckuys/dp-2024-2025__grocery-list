# Rapport Final

## Ce qu'on a pas eu le temps de faire

- Gérer plusieurs magasins pour les courses    
- Prise en charge complète de différentes sources de donnée

## Ce qui a été difficile

- Gérer proprement la logique des commandes en ligne de commande  
- Organiser les responsabilités entre les classes (éviter les dépendances circulaires)

## Quel design pattern avez-vous utilisés et pourquoi ?

- **design pattern (Command Pattern)** : chaque commande (`AddCommand`, `RemoveCommand`, etc.) implémente l’interface `Command`. Cela permet d’ajouter facilement de nouvelles commandes.
- **design pattern (DAO)** —  permet de séparer la logique d’accès aux données (csv,json) de la logique métier , afin de faciliter la maintenance, les tests, et l’évolution du code

---

## Questions

### Comment ajouter une nouvelle commande (en théorie, sans code) ?
Créer une classe qui implémente `Command`, et l’ajouter dans le `switch` sur `commandName` dans `Main.java`.

### Comment ajouter une nouvelle source de données (en théorie, sans code) ?
Créer une nouvelle classe qui implémente une l'interface DAO ou hérite d’une classe existante de lecture/écriture. Injecter cette classe dans la commande concernée.

### Que dois-je changer si je veux spécifier un magasin dans lequel ajouter mes courses (en théorie, sans code) ?
Ajouter un champ `store` à `ProductItem`, modifier les commandes pour qu’elles prennent ce paramètre en entrée, et adapter la logique d’affichage ou de stockage.

