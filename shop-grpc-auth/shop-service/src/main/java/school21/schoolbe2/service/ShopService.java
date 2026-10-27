package school21.schoolbe2.service;

import school21.schoolbe2.dto.*;
import school21.schoolbe2.persistence.entity.ClientEntity;
import school21.schoolbe2.persistence.entity.ProductEntity;

import java.util.List;
import java.util.UUID;

public interface ShopService {
    ProductEntity GetSuppEntImageEnt(ProductDto productDto);

    ClientDto AddClient(ClientDto clientDto);

    void RemoveClientId(UUID id);

    List<ClientDto> GetClientNameSurname(String name, String Surname);

    List<ClientDto> convert(List<ClientEntity> clientEntities);

    List<ClientDto> getAllClient(Integer limit, Integer offset);

    ClientDto updateAddress(UUID id, AddressDto addressDto);

    ProductDto AddNewProdact(ProductDto productDto);

    ProductDto ReduceProduct(UUID productId, int reduce);

    ProductDto GetProductId(UUID id);

    List<ProductDto> GetAllAvailableProduct();

    void DeleteProductById(UUID productId);

    SupplierDto addSupplier(SupplierDto supplierDto);

    SupplierDto updateAddress(AddressDto addressDto, UUID id);

    void deleteSupplierById(UUID id);

    List<SupplierDto> getAllSupplier();

    SupplierDto getSupplierById(UUID id);

    ImageDto addImage(byte[] array, UUID productId);

    ImageDto updateImage(UUID imageId, byte[] arr);

    void deleteImageById(UUID id);

    byte[] getImageProduct(UUID productId);

    byte[] getImageById(UUID imageId);
}
