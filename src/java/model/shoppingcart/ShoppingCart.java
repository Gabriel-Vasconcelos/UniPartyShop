package model.shoppingcart;

import java.util.ArrayList;
import java.util.List;
import model.product.ProductDAO;

public class ShoppingCart {

    private static final String SEPARADOR_ITEM = "S";
    private static final String SEPARADOR_CAMPOS = "C";

    /**
     * Método para adicionar um novo produto ao carrinho de compras
     *
     * @param cookieString
     * @param productId
     * @return
     */
    public static String insert(String cookieString, int productId) {
        if (cookieString != null && cookieString.length() > 0) {
            String[] items = cookieString.split(SEPARADOR_ITEM);
            cookieString = "";
            boolean isInserted = false;
            for (int i = 0; i < items.length; i++) {
                String[] item = items[i].split(SEPARADOR_CAMPOS);
                int id = Integer.parseInt(item[0]);
                int quantify = Integer.parseInt(item[1]);
                if (id == productId) {
                    quantify++;
                    isInserted = true;
                }
                if (cookieString.isEmpty()) {
                    cookieString += id + SEPARADOR_CAMPOS + quantify;
                } else {
                    cookieString += SEPARADOR_ITEM + id + SEPARADOR_CAMPOS + quantify;
                }
            }
            if (!isInserted) {
                cookieString += SEPARADOR_ITEM + productId + SEPARADOR_CAMPOS + 1;
            }
        } else {
            cookieString = productId + SEPARADOR_CAMPOS + 1;
        }
        return cookieString;
    }

    /**
     * Método utilizado para transformar a string do cookie em uma lista de
     * itens do carrinho de compra
     *
     * @param cookieString
     * @return
     */
    public static List<ShoppingCartItem> getCart(String cookieString) {
        List<ShoppingCartItem> result = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO();
        if (cookieString != null && cookieString.length() > 0) {
            String[] items = cookieString.split(SEPARADOR_ITEM);
            for (String register : items) {

                String[] item = register.split(SEPARADOR_CAMPOS);
                int id = Integer.parseInt(item[0]);
                int quantify = Integer.parseInt(item[1]);

                ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
                shoppingCartItem.setProduct(productDAO.selectProduct(id));
                shoppingCartItem.setQuantify(quantify);

                result.add(shoppingCartItem);
            }
        }
        return result;
    }

    /**
     * Método utilizado para remover um produto do carrinho de compras
     *
     * @param cookieString
     * @param productId
     * @return
     */
    public static String removeProductCart(String cookieString, int productId) {
        List<ShoppingCartItem> items = getCart(cookieString);
        cookieString = "";
        for (ShoppingCartItem register : items) {
            if (register.getProduct().getId() == productId) {
                continue;
            } else {
                if (cookieString.isEmpty()) {
                    cookieString = register.getProduct().getId() + SEPARADOR_CAMPOS + register.getQuantify();
                } else {
                    cookieString += SEPARADOR_ITEM + register.getProduct().getId() + SEPARADOR_CAMPOS + register.getQuantify();
                }
            }

        }
        return cookieString;
    }

    /**
     * Método utilizado para remover ou adicionar uma quantidade do produto do
     * carrinho de compras
     *
     * @param cookieString
     * @param productId
     * @param action add or remove
     *
     * @return
     */
    public static String removeAddProductQuantify(String cookieString, int productId, String action) {

        if (cookieString != null && cookieString.length() > 0) {
            String[] items = cookieString.split(SEPARADOR_ITEM);

            cookieString = "";

            for (int i = 0; i < items.length; i++) {
                String[] item = items[i].split(SEPARADOR_CAMPOS);

                int id = Integer.parseInt(item[0]);
                int quantify = Integer.parseInt(item[1]);

                if (id == productId && action.equals("add")) {
                    quantify++;
                }
                if (id == productId && action.equals("remove")) {
                    quantify--;
                }
                if (cookieString.isEmpty()) {
                    cookieString += id + SEPARADOR_CAMPOS + quantify;
                }
            }

        }
        return cookieString;

    }
}
