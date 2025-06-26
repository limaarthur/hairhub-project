CREATE TABLE establishment (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    cnpj VARCHAR(255) NOT NULL,
    img_url VARCHAR(255) NOT NULL,
    address_id UUID NOT NULL,
    FOREIGN KEY (address_id) REFERENCES address(id)
);