package com.udea.bookclub.services;

import com.udea.bookclub.dtos.BookClubDTO;
import com.udea.bookclub.dtos.mappers.IBookClubMapper;
import com.udea.bookclub.exceptions.RepositoryException;
import com.udea.bookclub.models.BookClub;
import com.udea.bookclub.repositories.IBookClubRepository;
import com.udea.bookclub.services.facade.IBookClubService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookClubService implements IBookClubService {

    private final IBookClubRepository bookClubRepository;
    private final IBookClubMapper bookClubMapper;

    public BookClubService(IBookClubRepository bookClubRepository, IBookClubMapper bookClubMapper) {
        this.bookClubRepository = bookClubRepository;
        this.bookClubMapper = bookClubMapper;
    }

    @Override
    public BookClubDTO save(BookClubDTO bookClubDTO) throws RepositoryException {
        BookClub bookClub = bookClubMapper.toBookClub(bookClubDTO);
        return bookClubMapper.toBookClubDTO(bookClubRepository.save(bookClub));
    }

    @Override
    public List<BookClubDTO> findAll(Pageable pageable) throws RepositoryException {
        return bookClubMapper.toBookClubsDTO(bookClubRepository.findAll(pageable).toList());
    }

    @Override
    public BookClubDTO findById(Long id) throws RepositoryException {
        Optional<BookClub> bookClub = bookClubRepository.findById(id);
        if (bookClub.isEmpty()){
            throw new RepositoryException("BookClub not found");
        }
        return bookClubMapper.toBookClubDTO(bookClub.get());
    }

    @Override
    public BookClubDTO update(BookClubDTO bookClubDTO) throws RepositoryException {
        Optional<BookClub> bookClub = bookClubRepository.findById(bookClubDTO.clubId());
        if (bookClub.isEmpty()){
            throw new RepositoryException("BookClub not found");
        }
        return bookClubMapper.toBookClubDTO(bookClubRepository.save(bookClub.get()));
    }

    @Override
    public void deleteById(Long id) throws RepositoryException {
        Optional<BookClub>  bookClub = bookClubRepository.findById(id);
        if (bookClub.isEmpty()){
            throw new RepositoryException("BookClub not found");
        }
        bookClubRepository.deleteById(id);
    }
}
