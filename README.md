# VK_SRE
SRE Quest

# Requirements:
- Ansible 2.14.
- Generated keys.
- Access to the host via key.

For run playbook run
```
ansible-playbook configure_playbook.yaml -i inventory.yaml --private-key=/path/to/private/key
```
