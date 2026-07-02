import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.AlreadyExistsException;
import ru.netology.NotFoundException;
import ru.netology.Product;
import ru.netology.ShopRepository;

public class ShopRepositoryTest {

    ShopRepository repo = new ShopRepository();
    Product product1 = new Product(1, "Рубашка", 1500);
    Product product2 = new Product(2, "Комплект постельного белья", 3000);
    Product product3 = new Product(3,"Форма для запекания", 700);

    @Test
    public void shouldRemoveExistingElement() {
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        repo.remove(2);

        Product[] expected = {product1, product3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenElementNotExist () {
        repo.add(product1);
        repo.add(product2);

        Assertions.assertThrows(NotFoundException.class, () -> {repo.remove(3);
        });
    }

    @Test
    public void shouldAddProductSuccessfully() {
        repo.add(product2);
        repo.add(product3);

        Product[] expected = {product2, product3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowAlreadyExistsExceptionWhenIdDuplicated() {
        repo.add(product1);
        repo.add(product3);

        Product duplicateProduct = new Product(3, "Чайник", 700);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(duplicateProduct);
        });
    }
}
