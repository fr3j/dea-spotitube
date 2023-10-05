package nl.frej.dea.spotitube.dao;

import jakarta.enterprise.inject.Alternative;
import nl.frej.dea.spotitube.dao.interfaces.Dao;
import nl.frej.dea.spotitube.services.dto.PlaylistDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Alternative
public class HardcodedDao implements Dao<PlaylistDTO> {
    private List<PlaylistDTO> playlists = new ArrayList<>();

    public HardcodedDao(){
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setId(1);
        playlistDTO.setName("Electronic playlist 2020");
        playlistDTO.setOwner(false);
        playlists.add(playlistDTO);

        PlaylistDTO playlistDTO2 = new PlaylistDTO();
        playlistDTO2.setId(2);
        playlistDTO2.setName("Electronic playlist 2021");
        playlistDTO2.setOwner(false);
        playlists.add(playlistDTO2);
    }

    @Override
    public Optional get(long id) {
        return Optional.empty();
    }

    @Override
    public List<PlaylistDTO> findAll() {
        return playlists;
    }

    @Override
    public void save(PlaylistDTO playlistDTO) {

    }

    @Override
    public void update(PlaylistDTO playlistDTO, String[] params) {

    }

    @Override
    public void delete(PlaylistDTO playlistDTO) {

    }
}
