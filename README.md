<p align="center">
<img src="https://github.com/gborneGit/gborneGit/blob/main/EcoCraft_logo_transparent.png" width="100"/>
</p>

# Plugin Minecraft EcoCraft pour la promotion du serveur grâce aux votes en ligne

Le plugin Minecraft EcoCraft pour la connexion aux serveurs de votes est un outil indispensable pour les joueurs et les propriétaires de serveurs qui souhaitent promouvoir leur serveur. Le plugin permet aux joueurs de voter pour le serveur sur différents sites de vote en ligne et d'offrir une récompense, ce qui peut aider à attirer de nouveaux joueurs et à augmenter la visibilité du serveur.

Le plugin est facile à installer et à configurer, et il fonctionne de manière transparente en arrière-plan sans perturber l'expérience de jeu. Les joueurs peuvent voter pour le serveur en utilisant des commandes simples en jeu, et le plugin enregistre automatiquement les votes sur les différents sites de vote.

## Configuration

* `name` : Le nom du serveur de vote.
* `delay` : Le temps en minutes entre les votes sur ce serveur de vote.
* `server_id` : L'ID du serveur sur le site de vote.
* `url_vote` : L'URL utilisée pour voter pour le serveur. `{server}` est remplacé par l'ID du serveur lors de l'envoi de la requête.
* `url_check` : L'URL utilisée pour vérifier si un vote a été effectué. `{server}` est remplacé par l'ID du serveur et `{ip}` est remplacé par l'adresse IP du joueur lors de l'envoi de la requête.

Le plugin utilise ces informations pour se connecter aux différents serveurs de votes et permettre aux joueurs de voter pour le serveur EcoCraft.
