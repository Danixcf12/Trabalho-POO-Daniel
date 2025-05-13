
package POO;

/**
 * Class responsible for managing a list of authorizers.
 * Implements the AuthorizerOperations interface.
 */
public class AuthorizerManagement implements AuthorizerOperations {

    // -------- Attributes --------
    private String[] authorizers = new String[100];  // Array para armazenar os nomes dos autorizadores
    private int authorizerCount = 0;  // Contador de quantos autorizadores foram adicionados

    // -------- Methods (Implementations) --------

    /**
     * Adds a new authorizer's name to the list.
     * @param name The name of the authorizer to be added
     */
    @Override
    public void addAuthorizer(String name) {
        if (authorizerCount < authorizers.length) {
            authorizers[authorizerCount++] = name;
        }
    }

    /**
     * Removes an authorizer's name from the list.
     * @param name The name of the authorizer to be removed
     * @return true if the authorizer existed and was removed, false otherwise
     */
    @Override
    public boolean removeAuthorizer(String name) {
        for (int i = 0; i < authorizerCount; i++) {
            if (authorizers[i].equals(name)) {
                // Move todos os elementos para a esquerda
                for (int j = i; j < authorizerCount - 1; j++) {
                    authorizers[j] = authorizers[j + 1];
                }
                authorizers[--authorizerCount] = null;  // Decrease count and clear the last element
                return true;
            }
        }
        return false;
    }

    /**
     * Lists all the authorizers' names currently stored.
     * @return A new array containing all authorizer names
     */
    @Override
    public String[] listAuthorizers() {
        String[] result = new String[authorizerCount];
        for (int i = 0; i < authorizerCount; i++) {
            result[i] = authorizers[i];
        }
        return result; // Retorna um array com os nomes dos autorizadores
    }

    /**
     * Edits the name of an existing authorizer.
     * @param oldName The current name of the authorizer
     * @param newName The new name to replace the old one
     * @return true if the name was found and updated, false if not
     */
    public boolean editAuthorizer(String oldName, String newName) {
        for (int i = 0; i < authorizerCount; i++) {
            if (authorizers[i].equals(oldName)) {
                authorizers[i] = newName;
                return true;
            }
        }
        return false;
    }
}
