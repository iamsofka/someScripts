package tests;

import dtos.DTOBase;
import org.junit.After;
import org.junit.Before;
import repositories.IRepository;

public abstract class RepositoryTestBase<TEntity extends DTOBase, TRepository extends IRepository<TEntity>> {

    private TRepository _repository;

    @Before
    public void before() {
        _repository = Create();
        if (_repository != null) {
            _repository.beginTransaction();
        }
    }

    @After
    public void after() {
        if (_repository != null) {
            _repository.rollbackTransaction();
        }
    }

    public TRepository get_repository() {
        return _repository;
    }

    public void addToRepository(TEntity tEntity) {
        _repository.add(tEntity);
    }

    protected abstract TRepository Create();
}