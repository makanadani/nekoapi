/*
 * @author Marina Yumi Kanadani - RM 558404 - 1TDSPX
 */

package br.com.nekoapi.model.bo;

import br.com.nekoapi.model.vo.CatVO;
import br.com.nekoapi.service.CatService;
import br.com.nekoapi.model.dao.CatDAO;

import java.io.IOException;
import java.util.List;

public class CatBO {

    private final CatDAO catDAO = new CatDAO();

    // Método para obter todos os gatos do banco de dados
    public List<CatVO> readAllCats() {
        return catDAO.findAll();
    }

    // Método para obter um gato pelo ID
    public CatVO readCatById(String id) {
        return catDAO.findById(id);
    }

    // Método para criar um novo gato
    public boolean createCat(CatVO cat) {
        // Verifica se o gato já existe
        if (catDAO.findById(cat.getId()) == null) {
            return catDAO.create(cat);
        }
        return false; // Retorna false se o gato já existir
    }

    // Método para atualizar um gato existente
    public boolean updateCat(String id, CatVO updatedCat) {
    	CatVO existingCat = catDAO.findById(id);
        if (existingCat != null) {
            existingCat.setName(updatedCat.getName());
            existingCat.setDescription(updatedCat.getDescription());
            existingCat.setTemperament(updatedCat.getTemperament());
            existingCat.setOrigin(updatedCat.getOrigin());
            existingCat.setLifeSpan(updatedCat.getLifeSpan());
            existingCat.setImageUrl(updatedCat.getImageUrl()); // Inclui a URL da imagem
            return catDAO.update(existingCat);
        }
        return false;
    }

    // Método para deletar um gato pelo ID
    public boolean deleteCat(String id) {
        return catDAO.delete(id);
    }

    // Método para buscar raças de gatos na API externa e salvar no banco de dados
    public boolean fetchAndSaveCatsFromAPI() {
        try {
        	CatVO[] catsFromAPI = CatService.searchCatBreed();
            if (catsFromAPI != null) {
                for (CatVO cat : catsFromAPI) {
                    // Verifica se a raça já existe no banco antes de salvar
                    if (catDAO.findById(cat.getId()) == null) {
                        catDAO.create(cat);
                    }
                }
                return true;
            }
        } catch (IOException e) {
            System.err.println("Erro ao buscar dados da API externa: " + e.getMessage());
        }
        return false;
    }
}
