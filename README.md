Ce projet constitue l’API REST sécurisée de l’application digital banking, développée avec Spring Boot. Il prend en charge l’authentification, la gestion des comptes, des clients et des opérations pour trois types d’utilisateurs : ADMIN, AGENT, et CLIENT.

Architecture de l’application
Le backend repose sur les modules Spring suivants :

Spring Security avec JWT : Authentifie les utilisateurs via des tokens JWT signés. Un filtre de sécurité (JwtAuthenticationFilter) intercepte toutes les requêtes sauf celles destinées à l’authentification.

Contrôle d’accès par rôle : Les permissions sont gérées via des annotations ou une configuration globale qui filtre l’accès selon le rôle de l’utilisateur connecté.

Services métier : Couche métier exposant des opérations via les services ClientService, CompteService, et OperationService.

Structure REST : Les ressources sont exposées via des contrôleurs REST (/api/auth, /api/clients, /api/comptes, /api/operation).

Fonctionnalités principales
Authentification sécurisée :

Point d’entrée /auth/\*\* pour l’authentification (login)

Retourne un token JWT signé en cas de succès

Authentification basée sur Spring Security et les rôles utilisateur

Filtrage par rôles et sécurisation des routes :

Un filtre JWT protège toutes les routes sauf /auth/\*\*

Les accès sont configurés selon le rôle :

Les CLIENT peuvent consulter ou modifier leurs comptes et effectuer des opération entre leurs comptes et les comptes d'autres clients.

Les AGENT peuvent consulter ou modifier les comptes et effectuer tous les opérations

Gestion des droits par contrôleur :

Exemples de règles :

GET /comptes/\*\* : accès ADMIN ou AGENT

POST /clients : accès réservé à AGENT

DELETE /clients/\*\* : accès réservé à AGENT

Toute tentative d’accès non autorisée retourne une réponse 403
