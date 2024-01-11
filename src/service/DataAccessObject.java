package service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import daoservice.DAOHttpService;
import daoservice.DAOJsonService;
import daoservice.LagerFXModel;

public class DataAccessObject implements Speicherbar<DataTransferObject> {

    private Function<LagerFXModel,DataTransferObject> LagerFXtoDTO = (v) -> {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new DataTransferObject(v.getId(), v.getUserId(), v.getTitle() , v.getBody());
    };

    @Override
    public List<DataTransferObject> getAll() {
        return DAOJsonService.deserialize(DAOHttpService.getJSONOffline()).stream()
                .map(LagerFXtoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean create(DataTransferObject data) {
        return false;
    }

    @Override
    public boolean delete(DataTransferObject data) {
        return false;
    }
}
