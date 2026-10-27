package school21.schoolbe2.service;

import org.springframework.stereotype.Service;
import school21.schoolbe2.dto.*;
import school21.schoolbe2.exception.BadRequestException;
import school21.schoolbe2.exception.NotFoundException;
import school21.schoolbe2.mapper.Mapper;
import school21.schoolbe2.persistence.entity.*;
import school21.schoolbe2.persistence.repository.*;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SchopService {
    private SupplierRepository supplierRepository;
    private ImageRepository imageRepository;
    private Mapper mapper;
    private AddressRepository addressRepository;
    private ClientRepository clientRepository;
    private ProductRepository productRepository;

    public SchopService(SupplierRepository supplierRepository, ImageRepository imageRepository, Mapper mapper, AddressRepository addressRepository, ClientRepository clientRepository, ProductRepository productRepository) {
        this.supplierRepository = supplierRepository;
        this.imageRepository = imageRepository;
        this.mapper = mapper;
        this.addressRepository = addressRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    public ProductEntity GetSuppEntImageEnt(ProductDto productDto) {
        if (productDto == null) {
            throw new BadRequestException("Object is null");
        }
        UUID image_id = productDto.getImageId();
        UUID supplier_id = productDto.getSupplierId();
        if (supplier_id == null || image_id == null) {
            throw new BadRequestException("Id is null");
        }
        return mapper.ProDtoToEntity(productDto, supplierRepository.findById(supplier_id).orElseThrow(() -> new NotFoundException("Supplier not found")), imageRepository.findById(image_id).orElseThrow(() -> new NotFoundException("Product not found")));
    }

    public ClientDto AddClient(ClientDto clientDto) {
        if (clientDto == null) {
            throw new BadRequestException("Client is null");
        }
        ClientEntity clientEntity = mapper.ClientDtoToEntity(clientDto);
        addressRepository.save(clientEntity.getAddress());
        return mapper.ClientEntityToDto(clientRepository.save(clientEntity));
    }

    public void RemoveClientId(UUID id) {
        if (id == null) {
            throw new BadRequestException("Id is null");
        }
        if (!clientRepository.existsById(id)) {
            throw new NotFoundException("Client not found");
        }
        ClientEntity client = clientRepository.findById(id).get();
        AddressEntity address = client.getAddress();
        clientRepository.deleteById(id);
        addressRepository.deleteById(address.getAddressId());
    }

    public List<ClientDto> GetClientNameSurname(String name, String Surname) {
        if (name == null || Surname == null || name.isEmpty() || Surname.isEmpty()) {
            throw new BadRequestException("Name or Surname not found");
        }
        List<ClientEntity> clientEntities = clientRepository.findByClientNameAndClientSurname(name, Surname);
        List<ClientDto> clientDtos = new ArrayList<>();
        for (ClientEntity client : clientEntities) {
            clientDtos.add(mapper.ClientEntityToDto(client));
        }
        return clientDtos;
    }

    public List<ClientDto> convert(List<ClientEntity> clientEntities) {
        List<ClientDto> clientDtos = new ArrayList<>();
        for (ClientEntity client : clientEntities) {
            clientDtos.add(mapper.ClientEntityToDto(client));
        }
        return clientDtos;
    }

    public List<ClientDto> getAllClient(Integer limit, Integer offset) {
        if ((limit != null && limit < 0) || (offset != null && offset < 0)) {
            throw new BadRequestException("Error Limit or Offset negative");
        }
        List<ClientDto> clientDtos = new ArrayList<>();
        if (limit == null && offset == null) {
            Iterable<ClientEntity> clientEntities = clientRepository.findAll();
            for (ClientEntity client : clientEntities) {
                clientDtos.add(mapper.ClientEntityToDto(client));
            }
            return clientDtos;
        }
        if (limit != null && offset == null) {
            return convert(clientRepository.findAllClientLimitAndOffset(limit, 0));
        }
        if (limit == null && offset != null) {
            return convert(clientRepository.findAllClientLimitAndOffset(null, offset));
        }

        return convert(clientRepository.findAllClientLimitAndOffset(limit, offset));
    }

    public ClientDto updateAddress(UUID id, AddressDto addressDto) {
        if (id == null || addressDto == null) {
            throw new BadRequestException("Id or object is null");
        }
        ClientEntity client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Client not found"));

        AddressEntity address = client.getAddress();
        address.setCity(addressDto.getCity());
        address.setCountry(addressDto.getCountry());
        address.setStreet(addressDto.getStreet());
        addressRepository.save(address);
        client.setAddress(address);
        return mapper.ClientEntityToDto(clientRepository.save(client));
    }

    public ProductDto AddNewProdact(ProductDto productDto) {
        if (productDto == null) {
            throw new BadRequestException("Object is null");
        }
        if (productDto.getSupplierId() == null) {
            throw new BadRequestException("Error");
        }
        SupplierEntity supplier = supplierRepository.findById(productDto.getSupplierId()).orElseThrow(() -> new NotFoundException("Supplier id not found"));
        ImageEntity imageEntity = null;
        if (productDto.getImageId() != null) {
            imageEntity = imageRepository.findById(productDto.getImageId()).orElseThrow(() -> new NotFoundException("Image id not found"));
        }
        ProductEntity productEntity = mapper.ProDtoToEntity(productDto, supplier, imageEntity);
        return mapper.ProEntityToDto(productRepository.save(productEntity));
    }

    public ProductDto ReduceProduct(UUID productId, int reduce) {
        if (productId == null) {
            throw new BadRequestException("Id is null");
        }
        if (reduce < 0) {
            throw new BadRequestException("Error. reduce < 0");
        }
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
        if (reduce > productEntity.getAvailableStock()) {
            throw new BadRequestException("Error.Reduce > stock");
        }
        productEntity.setAvailableStock(productEntity.getAvailableStock() - reduce);
        return mapper.ProEntityToDto(productRepository.save(productEntity));
    }

    public ProductDto GetProductId(UUID id) {
        if (id == null) {
            throw new BadRequestException("Id is null");
        }
        return mapper.ProEntityToDto(productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found")));
    }

    public List<ProductDto> GetAllAvailableProduct() {
        List<ProductDto> productDtos = new ArrayList<>();
        List<ProductEntity> productEntities = productRepository.findByAvailableStockGreaterThan(0);
        for (ProductEntity pr : productEntities) {
            productDtos.add(mapper.ProEntityToDto(pr));
        }
        return productDtos;
    }

    public void DeleteProductById(UUID productId) {
        if (productId == null) {
            throw new BadRequestException("Id is null");
        }
        if (!productRepository.existsById(productId)) {
            throw new NotFoundException("Product not found");
        }
        productRepository.deleteById(productId);
    }

    public SupplierDto addSupplier(SupplierDto supplierDto) {
        if (supplierDto == null) {
            throw new BadRequestException("Error.Object is null");
        }
        SupplierEntity supplier = mapper.SuppDtoToEntity(supplierDto);
        addressRepository.save(supplier.getAddress());
        return mapper.SuppEntityToDto(supplierRepository.save(supplier));
    }

    public SupplierDto updateAddress(AddressDto addressDto, UUID id) {
        if (addressDto == null || id == null) {
            throw new BadRequestException("Error.Object is null or id is null or address is null");
        }
        SupplierEntity supplier = supplierRepository.findById(id).orElseThrow(() -> new NotFoundException("Supplier not found"));
        AddressEntity address = supplier.getAddress();
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setCountry(addressDto.getCountry());
        supplier.setAddress(address);
        addressRepository.save(address);
        return mapper.SuppEntityToDto(supplierRepository.save(supplier));
    }

    public void deleteSupplierById(UUID id) {
        if (id == null) {
            throw new BadRequestException("Error. Id is null");
        }
        SupplierEntity supplier = supplierRepository.findById(id).orElseThrow(() -> new NotFoundException("Supplier not found"));
        if (productRepository.existsBySupplierSupplierId(id)) {
            throw new BadRequestException("Supplier has products and cannot be deleted");
        }
        AddressEntity address = supplier.getAddress();
        supplierRepository.deleteById(id);
        addressRepository.deleteById(address.getAddressId());
    }

    public List<SupplierDto> getAllSupplier() {
        Iterable<SupplierEntity> supplierEntities = supplierRepository.findAll();
        List<SupplierDto> supplierDtos = new ArrayList<>();
        for (SupplierEntity s : supplierEntities) {
            supplierDtos.add(mapper.SuppEntityToDto(s));
        }
        return supplierDtos;
    }

    public SupplierDto getSupplierById(UUID id) {
        if (id == null) {
            throw new BadRequestException("Error.Id is null");
        }
        return mapper.SuppEntityToDto(supplierRepository.findById(id).orElseThrow(() -> new NotFoundException("Error. Supplier not found")));
    }

    private boolean isImage(byte[] array) {
        if (array == null || array.length == 0) {
            return false;
        }

        try {
            return ImageIO.read(new ByteArrayInputStream(array)) != null;
        } catch (IOException e) {
            return false;
        }
    }

    public ImageDto addImage(byte[] array, UUID productId){
        if (productId == null || !isImage(array)){
            throw new BadRequestException("Error. Image is empty or invalid");
        }

        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Error. Product not found"));
        ImageEntity imageEntity = new ImageEntity(array);
        ImageEntity saveImage = imageRepository.save(imageEntity);
        productEntity.setImageEntity(saveImage);
        productRepository.save(productEntity);
        return mapper.ImageEntityToDto(saveImage);
    }

    public ImageDto updateImage(UUID imageId, byte[] arr){
        if (imageId == null || !isImage(arr)){
            throw new BadRequestException("Error. Image is empty or invalid");
        }
        ImageEntity imageEntity = imageRepository.findById(imageId).orElseThrow(() -> new NotFoundException("Error. Image not found"));
        imageEntity.setImages(arr);
        return mapper.ImageEntityToDto(imageRepository.save(imageEntity));
    }

    public void deleteImageById(UUID id) {
        if (id == null) {
            throw new BadRequestException("Error. Id is null");
        }

        ImageEntity imageEntity = imageRepository.findById(id).orElseThrow(() -> new NotFoundException("Error. Image not found"));
        productRepository.findByImageEntityImageId(id).ifPresent(product -> {product.setImageEntity(null);productRepository.save(product);});
        imageRepository.delete(imageEntity);
    }

    public byte[] getImageProduct(UUID productId) {
        if (productId == null) {
            throw new BadRequestException("Error.Id is null");
        }
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Error. Product not found"));
        if (productEntity.getImageEntity() == null) {
            throw new NotFoundException("Image not found");
        }
        return productEntity.getImageEntity().getImages();
    }

    public byte[] getImageById(UUID imageId) {
        if (imageId == null) {
            throw new BadRequestException("Error. Image is null");
        }
        ImageEntity imageEntity = imageRepository.findById(imageId).orElseThrow(() -> new NotFoundException("Error.Image not found"));
        return imageEntity.getImages();
    }
}
