-- Créer la base de données
CREATE DATABASE petroleum_db;

-- Se connecter à la base de données créée (Décommentez cette ligne si vous exécutez ce script dans un environnement qui le permet)
-- \c petroleum_db

-- Créer une table pour les produits
CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    unit_price NUMERIC(10, 2) NOT NULL  -- Prix par unité avec deux décimales
);

-- Créer une table pour les transactions de stock
CREATE TABLE stock_transactions (
    id SERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    date_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
    movement_type VARCHAR(50) NOT NULL,
    quantity NUMERIC(10, 2) NOT NULL,  -- Quantité avec deux décimales
    FOREIGN KEY (product_id) REFERENCES product (id)
);

-- Créer un utilisateur avec un mot de passe (remplacez 'yourPassword' par un mot de passe sécurisé)
CREATE USER petroleum_user WITH ENCRYPTED PASSWORD 'yourPassword';

-- Donner les droits nécessaires à l'utilisateur
GRANT CONNECT ON DATABASE petroleum_db TO petroleum_user;
GRANT USAGE ON SCHEMA public TO petroleum_user;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO petroleum_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO petroleum_user;

-- Configuration supplémentaire pour les futures tables et les séquences créées
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO petroleum_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL PRIVILEGES ON SEQUENCES TO petroleum_user;
