# VK_SRE
SRE Quest

jenkins_groovy - Pipelines for deploy LAMP, dumb db, sql_query.

lamp_ubuntu2004 - Ansible playbooks for deploy and configure LAMP

# Requirements:
- Ansible 2.14.
- Generated keys.
- Access to the host via key.

For run playbook run
```
ansible-playbook configure_playbook.yaml -i inventory.yaml --private-key=/path/to/private/key
```
