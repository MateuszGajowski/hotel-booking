CREATE USER IF NOT EXISTS SA SALT 'f75a51f2ac5d5021' HASH '2f2d5d7d1aeb6b5e813357da0c97512d3e30a0cd68e84e73d85c6fe1ab6c590d' ADMIN;
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_9C384F58_A664_493A_877D_BDC1DDA17CE9 START WITH 2 BELONGS_TO_TABLE;
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_D302FCAB_99BD_4A3A_9D64_4684FE2EDC7C START WITH 1 BELONGS_TO_TABLE;
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_3BF6E668_BA52_4F42_9FC9_741C6F4BF57E START WITH 1 BELONGS_TO_TABLE;
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_30026C8B_76F2_4047_8C07_10796DE13249 START WITH 2 BELONGS_TO_TABLE;
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_DAD1665D_9FAA_4DF6_83E9_C2EF1B0809F9 START WITH 2 BELONGS_TO_TABLE;
CREATE MEMORY TABLE PUBLIC.BOOKING( 
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_3BF6E668_BA52_4F42_9FC9_741C6F4BF57E) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_3BF6E668_BA52_4F42_9FC9_741C6F4BF57E, 
    CREATED_DATE TIMESTAMP NOT NULL, 
    MODIFIED_DATE TIMESTAMP, 
    CONFIRMATION_CODE VARCHAR(255) NOT NULL, 
    DATE_IN DATE NOT NULL, 
    DATE_OUT DATE NOT NULL, 
    STATUS INTEGER NOT NULL, 
    CUSTOMER_ID BIGINT, 
    ROOM_ID BIGINT 
);
ALTER TABLE PUBLIC.BOOKING ADD CONSTRAINT PUBLIC.CONSTRAINT_2 PRIMARY KEY(ID);
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.BOOKING;
CREATE MEMORY TABLE PUBLIC.CUSTOMER( 
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_D302FCAB_99BD_4A3A_9D64_4684FE2EDC7C) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_D302FCAB_99BD_4A3A_9D64_4684FE2EDC7C, 
    CREATED_DATE TIMESTAMP NOT NULL, 
    MODIFIED_DATE TIMESTAMP, 
    EMAIL VARCHAR(255) NOT NULL, 
    LOGIN VARCHAR(255) NOT NULL, 
    NAME VARCHAR(255) NOT NULL, 
    PASSWORD VARCHAR(255) NOT NULL, 
    PHONE_NUMBER VARCHAR(255) NOT NULL, 
    SURNAME VARCHAR(255) NOT NULL, 
    TOKEN VARCHAR(255) NOT NULL 
);
ALTER TABLE PUBLIC.CUSTOMER ADD CONSTRAINT PUBLIC.CONSTRAINT_5 PRIMARY KEY(ID);
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.CUSTOMER;
CREATE MEMORY TABLE PUBLIC.HOTEL( 
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_9C384F58_A664_493A_877D_BDC1DDA17CE9) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_9C384F58_A664_493A_877D_BDC1DDA17CE9, 
    CREATED_DATE TIMESTAMP NOT NULL, 
    MODIFIED_DATE TIMESTAMP, 
    ADDRESS VARCHAR(255) NOT NULL, 
    CITY VARCHAR(255) NOT NULL, 
    COUNTRY VARCHAR(255) NOT NULL, 
    NAME VARCHAR(255) NOT NULL, 
    POSTAL_CODE VARCHAR(255) NOT NULL 
);
ALTER TABLE PUBLIC.HOTEL ADD CONSTRAINT PUBLIC.CONSTRAINT_4 PRIMARY KEY(ID);
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.HOTEL;
CREATE MEMORY TABLE PUBLIC.ROOM( 
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_30026C8B_76F2_4047_8C07_10796DE13249) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_30026C8B_76F2_4047_8C07_10796DE13249, 
    CREATED_DATE TIMESTAMP NOT NULL, 
    MODIFIED_DATE TIMESTAMP, 
    FLOOR INTEGER NOT NULL,
    NUMBER VARCHAR(255) NOT NULL, 
    HOTEL_ID BIGINT, 
    TYPE_ID INTEGER 
);
ALTER TABLE PUBLIC.ROOM ADD CONSTRAINT PUBLIC.CONSTRAINT_26 PRIMARY KEY(ID);
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.ROOM;
CREATE MEMORY TABLE PUBLIC.ROOM_TYPE( 
    ID INTEGER DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_DAD1665D_9FAA_4DF6_83E9_C2EF1B0809F9) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_DAD1665D_9FAA_4DF6_83E9_C2EF1B0809F9, 
    CREATED_DATE TIMESTAMP NOT NULL, 
    MODIFIED_DATE TIMESTAMP, 
    CAPACITY INTEGER NOT NULL, 
    DESCRIPTION VARCHAR(255) NOT NULL, 
    PRICE DECIMAL(19, 2) NOT NULL, 
    CLASS INTEGER NOT NULL 
);
ALTER TABLE PUBLIC.ROOM_TYPE ADD CONSTRAINT PUBLIC.CONSTRAINT_6 PRIMARY KEY(ID);
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.ROOM_TYPE;
ALTER TABLE PUBLIC.ROOM ADD CONSTRAINT PUBLIC.FKF5VBGYPS3UBAKNN710NK2M5O5 FOREIGN KEY(TYPE_ID) REFERENCES PUBLIC.ROOM_TYPE(ID) NOCHECK;
ALTER TABLE PUBLIC.ROOM ADD CONSTRAINT PUBLIC.FKDOSQ3WW4H9M2OSIM6O0LUGNG8 FOREIGN KEY(HOTEL_ID) REFERENCES PUBLIC.HOTEL(ID) NOCHECK;
ALTER TABLE PUBLIC.BOOKING ADD CONSTRAINT PUBLIC.FKQ83PAN5XY2A6RN0QSL9BCKQAI FOREIGN KEY(ROOM_ID) REFERENCES PUBLIC.ROOM(ID) NOCHECK;
ALTER TABLE PUBLIC.BOOKING ADD CONSTRAINT PUBLIC.FKLNNELFSHA11XMO2NDJQ66FVRO FOREIGN KEY(CUSTOMER_ID) REFERENCES PUBLIC.CUSTOMER(ID) NOCHECK;