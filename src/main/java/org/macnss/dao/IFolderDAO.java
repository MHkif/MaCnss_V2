package org.macnss.dao;

import org.macnss.entity.Folder;

public interface IFolderDAO extends DAO<Folder> {
    final String TABLE = "folders";
    final String ID = "id";
    final String NAME = "name";
    final String DEPOSIT_DATE = "despositAt";
    final String STATUS = "status";
    final String TOTAL_REFUND = "total_refund";
    final String PATIENT_MATRICULATE = "matriculate";
    final String AGENT_ID = "agent_id";


}
