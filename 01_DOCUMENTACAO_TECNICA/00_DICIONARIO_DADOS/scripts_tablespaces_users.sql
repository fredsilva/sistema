 -- The name of the ASM instance is ASMDATA.
 -- Author: Juan León 
 -- Created Date: 1st April, 2016

-- TABLESPACES
CREATE TABLESPACE TBS_ARR_DADOS DATAFILE '+ASMDATA' SIZE 2048M;
CREATE TABLESPACE TBS_CAT_DADOS DATAFILE '+ASMDATA' SIZE 2048M;
CREATE TABLESPACE TBS_CCI_DADOS DATAFILE '+ASMDATA' SIZE 2048M;
CREATE TABLESPACE TBS_CCC_DADOS DATAFILE '+ASMDATA' SIZE 2048M;
CREATE TABLESPACE TBS_COB_DADOS DATAFILE '+ASMDATA' SIZE 2048M;
CREATE TABLESPACE TBS_COM_DADOS DATAFILE '+ASMDATA' SIZE 2048M;
CREATE TABLESPACE TBS_ECF_DADOS DATAFILE '+ASMDATA' SIZE 2048M;
CREATE TABLESPACE TBS_FIE_DADOS DATAFILE '+ASMDATA' SIZE 2048M;
CREATE TABLESPACE TBS_FTR_DADOS DATAFILE '+ASMDATA' SIZE 2048M;
CREATE TABLESPACE TBS_GED_DADOS DATAFILE '+ASMDATA' SIZE 2048M;
CREATE TABLESPACE TBS_ISE_DADOS DATAFILE '+ASMDATA' SIZE 2048M;
CREATE TABLESPACE TBS_NFE_DADOS DATAFILE '+ASMDATA' SIZE 2048M;
CREATE TABLESPACE TBS_OTR_DADOS DATAFILE '+ASMDATA' SIZE 2048M;
CREATE TABLESPACE TBS_REE_DADOS DATAFILE '+ASMDATA' SIZE 2048M;
CREATE TABLESPACE TBS_RES_DADOS DATAFILE '+ASMDATA' SIZE 2048M;
CREATE TABLESPACE TBS_SEG_DADOS DATAFILE '+ASMDATA' SIZE 2048M;
CREATE TABLESPACE TBS_WRK_DADOS DATAFILE '+ASMDATA' SIZE 2048M;
CREATE TABLESPACE TBS_ARR_INDICES DATAFILE '+ASMDATA' SIZE 1024M;
CREATE TABLESPACE TBS_CAT_INDICES DATAFILE '+ASMDATA' SIZE 1024M;
CREATE TABLESPACE TBS_CCI_INDICES DATAFILE '+ASMDATA' SIZE 1024M;
CREATE TABLESPACE TBS_CCC_INDICES DATAFILE '+ASMDATA' SIZE 1024M;
CREATE TABLESPACE TBS_COB_INDICES DATAFILE '+ASMDATA' SIZE 1024M;
CREATE TABLESPACE TBS_COM_INDICES DATAFILE '+ASMDATA' SIZE 1024M;
CREATE TABLESPACE TBS_ECF_INDICES DATAFILE '+ASMDATA' SIZE 1024M;
CREATE TABLESPACE TBS_FIE_INDICES DATAFILE '+ASMDATA' SIZE 1024M;
CREATE TABLESPACE TBS_FTR_INDICES DATAFILE '+ASMDATA' SIZE 1024M;
CREATE TABLESPACE TBS_GED_INDICES DATAFILE '+ASMDATA' SIZE 1024M;
CREATE TABLESPACE TBS_ISE_INDICES DATAFILE '+ASMDATA' SIZE 1024M;
CREATE TABLESPACE TBS_NFE_INDICES DATAFILE '+ASMDATA' SIZE 1024M;
CREATE TABLESPACE TBS_OTR_INDICES DATAFILE '+ASMDATA' SIZE 1024M;
CREATE TABLESPACE TBS_REE_INDICES DATAFILE '+ASMDATA' SIZE 1024M;
CREATE TABLESPACE TBS_RES_INDICES DATAFILE '+ASMDATA' SIZE 1024M;
CREATE TABLESPACE TBS_SEG_INDICES DATAFILE '+ASMDATA' SIZE 1024M;
CREATE TABLESPACE TBS_WRK_INDICES DATAFILE '+ASMDATA' SIZE 1024M;

-- USERS
CREATE USER SEFAZ_ARR IDENTIFIED BY SEFAZ_ARR DEFAULT TABLESPACE "TBS_ARR_DADOS" ACCOUNT UNLOCK;
CREATE USER SEFAZ_CAT IDENTIFIED BY SEFAZ_CAT DEFAULT TABLESPACE "TBS_CAT_DADOS" ACCOUNT UNLOCK;
CREATE USER SEFAZ_CCI IDENTIFIED BY SEFAZ_CCI DEFAULT TABLESPACE "TBS_CCI_DADOS" ACCOUNT UNLOCK;
CREATE USER SEFAZ_CCC IDENTIFIED BY SEFAZ_CCC DEFAULT TABLESPACE "TBS_CCC_DADOS" ACCOUNT UNLOCK;
CREATE USER SEFAZ_COB IDENTIFIED BY SEFAZ_COB DEFAULT TABLESPACE "TBS_COB_DADOS" ACCOUNT UNLOCK;
CREATE USER SEFAZ_COM IDENTIFIED BY SEFAZ_COM DEFAULT TABLESPACE "TBS_COM_DADOS" ACCOUNT UNLOCK;
CREATE USER SEFAZ_ECF IDENTIFIED BY SEFAZ_ECF DEFAULT TABLESPACE "TBS_ECF_DADOS" ACCOUNT UNLOCK;
CREATE USER SEFAZ_FIE IDENTIFIED BY SEFAZ_FIE DEFAULT TABLESPACE "TBS_FIE_DADOS" ACCOUNT UNLOCK;
CREATE USER SEFAZ_FTR IDENTIFIED BY SEFAZ_FTR DEFAULT TABLESPACE "TBS_FTR_DADOS" ACCOUNT UNLOCK;
CREATE USER SEFAZ_GED IDENTIFIED BY SEFAZ_GED DEFAULT TABLESPACE "TBS_GED_DADOS" ACCOUNT UNLOCK;
CREATE USER SEFAZ_ISE IDENTIFIED BY SEFAZ_ISE DEFAULT TABLESPACE "TBS_ISE_DADOS" ACCOUNT UNLOCK;
CREATE USER SEFAZ_NFE IDENTIFIED BY SEFAZ_NFE DEFAULT TABLESPACE "TBS_NFE_DADOS" ACCOUNT UNLOCK;
CREATE USER SEFAZ_OTR IDENTIFIED BY SEFAZ_OTR DEFAULT TABLESPACE "TBS_OTR_DADOS" ACCOUNT UNLOCK;
CREATE USER SEFAZ_REE IDENTIFIED BY SEFAZ_REE DEFAULT TABLESPACE "TBS_REE_DADOS" ACCOUNT UNLOCK;
CREATE USER SEFAZ_RES IDENTIFIED BY SEFAZ_RES DEFAULT TABLESPACE "TBS_RES_DADOS" ACCOUNT UNLOCK;
CREATE USER SEFAZ_SEG IDENTIFIED BY SEFAZ_SEG DEFAULT TABLESPACE "TBS_SEG_DADOS" ACCOUNT UNLOCK;
CREATE USER SEFAZ_WRK IDENTIFIED BY SEFAZ_WRK DEFAULT TABLESPACE "TBS_WRK_DADOS" ACCOUNT UNLOCK;
ALTER USER SEFAZ_ARR QUOTA UNLIMITED ON TBS_ARR_DADOS;
ALTER USER SEFAZ_ARR QUOTA UNLIMITED ON TBS_ARR_INDICES;
ALTER USER SEFAZ_CAT QUOTA UNLIMITED ON TBS_CAT_DADOS;
ALTER USER SEFAZ_CAT QUOTA UNLIMITED ON TBS_CAT_INDICES;
ALTER USER SEFAZ_CCI QUOTA UNLIMITED ON TBS_CCI_DADOS;
ALTER USER SEFAZ_CCI QUOTA UNLIMITED ON TBS_CCI_INDICES;
ALTER USER SEFAZ_CCC QUOTA UNLIMITED ON TBS_CCC_DADOS;
ALTER USER SEFAZ_CCC QUOTA UNLIMITED ON TBS_CCC_INDICES;
ALTER USER SEFAZ_COB QUOTA UNLIMITED ON TBS_COB_DADOS;
ALTER USER SEFAZ_COB QUOTA UNLIMITED ON TBS_COB_INDICES;
ALTER USER SEFAZ_COM QUOTA UNLIMITED ON TBS_COM_DADOS;
ALTER USER SEFAZ_COM QUOTA UNLIMITED ON TBS_COM_INDICES;
ALTER USER SEFAZ_ECF QUOTA UNLIMITED ON TBS_ECF_DADOS;
ALTER USER SEFAZ_ECF QUOTA UNLIMITED ON TBS_ECF_INDICES;
ALTER USER SEFAZ_FIE QUOTA UNLIMITED ON TBS_FIE_DADOS;
ALTER USER SEFAZ_FIE QUOTA UNLIMITED ON TBS_FIE_INDICES;
ALTER USER SEFAZ_FTR QUOTA UNLIMITED ON TBS_FTR_DADOS;
ALTER USER SEFAZ_FTR QUOTA UNLIMITED ON TBS_FTR_INDICES;
ALTER USER SEFAZ_GED QUOTA UNLIMITED ON TBS_GED_DADOS;
ALTER USER SEFAZ_GED QUOTA UNLIMITED ON TBS_GED_INDICES;
ALTER USER SEFAZ_ISE QUOTA UNLIMITED ON TBS_ISE_DADOS;
ALTER USER SEFAZ_ISE QUOTA UNLIMITED ON TBS_ISE_INDICES;
ALTER USER SEFAZ_NFE QUOTA UNLIMITED ON TBS_NFE_DADOS;
ALTER USER SEFAZ_NFE QUOTA UNLIMITED ON TBS_NFE_INDICES;
ALTER USER SEFAZ_OTR QUOTA UNLIMITED ON TBS_OTR_DADOS;
ALTER USER SEFAZ_OTR QUOTA UNLIMITED ON TBS_OTR_INDICES;
ALTER USER SEFAZ_REE QUOTA UNLIMITED ON TBS_REE_DADOS;
ALTER USER SEFAZ_REE QUOTA UNLIMITED ON TBS_REE_INDICES;
ALTER USER SEFAZ_RES QUOTA UNLIMITED ON TBS_RES_DADOS;
ALTER USER SEFAZ_RES QUOTA UNLIMITED ON TBS_RES_INDICES;
ALTER USER SEFAZ_SEG QUOTA UNLIMITED ON TBS_SEG_DADOS;
ALTER USER SEFAZ_SEG QUOTA UNLIMITED ON TBS_SEG_INDICES;
ALTER USER SEFAZ_WRK QUOTA UNLIMITED ON TBS_WRK_DADOS;
ALTER USER SEFAZ_WRK QUOTA UNLIMITED ON TBS_WRK_INDICES;

-- GRANT TO USER
GRANT CONNECT, RESOURCE TO SEFAZ_ARR;
GRANT CONNECT, RESOURCE TO SEFAZ_CAT;
GRANT CONNECT, RESOURCE TO SEFAZ_CCI;
GRANT CONNECT, RESOURCE TO SEFAZ_CCC;
GRANT CONNECT, RESOURCE TO SEFAZ_COB;
GRANT CONNECT, RESOURCE TO SEFAZ_COM;
GRANT CONNECT, RESOURCE TO SEFAZ_ECF;
GRANT CONNECT, RESOURCE TO SEFAZ_FIE;
GRANT CONNECT, RESOURCE TO SEFAZ_FTR;
GRANT CONNECT, RESOURCE TO SEFAZ_GED;
GRANT CONNECT, RESOURCE TO SEFAZ_ISE;
GRANT CONNECT, RESOURCE TO SEFAZ_NFE;
GRANT CONNECT, RESOURCE TO SEFAZ_OTR;
GRANT CONNECT, RESOURCE TO SEFAZ_REE;
GRANT CONNECT, RESOURCE TO SEFAZ_RES;
GRANT CONNECT, RESOURCE TO SEFAZ_SEG;
GRANT CONNECT, RESOURCE TO SEFAZ_WRK;
	
