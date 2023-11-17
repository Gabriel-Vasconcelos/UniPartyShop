function enableEdit(fieldId) {
    let field = document.getElementById(fieldId);
    let container = field.closest('.manage-account-item');

    if (container) {
        let editButton = container.querySelector('.editButton');
        let updateButton = container.querySelector('.updateButton');
        let cancelButton = container.querySelector('.cancelButton');

        if (editButton && updateButton && cancelButton) {
            field.readOnly = false;
            editButton.style.display = 'none';
            updateButton.style.display = 'inline-block';
            cancelButton.style.display = 'inline-block';
            
            if(field.name == 'password'){
                field.type = 'text';
            }
        } else {
            console.error('Elementos não encontrados ou estrutura HTML incorreta.');
        }
    } else {
        console.error('Não há mais elementos irmãos subsequentes.');
    }
}

function cancelEdit(fieldId) {
    let field = document.getElementById(fieldId);
    let container = field.closest('.manage-account-item');

    if (container) {
        let editButton = container.querySelector('.editButton');
        let updateButton = container.querySelector('.updateButton');
        let cancelButton = container.querySelector('.cancelButton');

        if (editButton && updateButton && cancelButton) {
            field.readOnly = true;
            field.value = field.defaultValue; // Restaura o valor padrão
            editButton.style.display = 'inline-block';
            updateButton.style.display = 'none';
            cancelButton.style.display = 'none';
            
            if(field.name == 'password'){
                field.type = 'password';
            }
        } else {
            console.error('Elementos não encontrados ou estrutura HTML incorreta.');
        }
    } else {
        console.error('Não há mais elementos irmãos subsequentes.');
    }
}
