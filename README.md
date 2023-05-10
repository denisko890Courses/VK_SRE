# VK_SRE
SRE Quest

jenkins_groovy - Pipelines for deploy LAMP and dump DB

lamp_ubuntu2004 - Ansible playbooks for deploy and configure LAMP

# Requirements:
- Ansible 2.14.
- Generated keys.
- Access to the host via key.

For run playbook run
```
ansible-playbook configure_playbook.yaml -i inventory.yaml --private-key=/path/to/private/key
```

Best practice(in Future):
1) Split playbook (Several playbooks and roles)
2) Use tags
3) Create and use special user for Ansible
