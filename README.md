# eap6-play

#### eap6-security
Basic Auth security test module

#### eap6-valve
Custom Valve security test module

#### eap6-ldap
Basic Auth + Ldap login module

dhcp-64-128:vagrant_ubuntu_openldap fabio$ ldapsearch -h localhost:3890 -D "cn=admin,dc=puppetlabs,dc=test" -w test -b "dc=puppetlabs,dc=test"
# extended LDIF
#
# LDAPv3
# base <dc=puppetlabs,dc=test> with scope subtree
# filter: (objectclass=*)
# requesting: ALL
#

# puppetlabs.test
dn: dc=puppetlabs,dc=test
objectClass: top
objectClass: dcObject
objectClass: organization
o: puppetlabs.test
dc: puppetlabs
description: puppetlabs.test

# admin, puppetlabs.test
dn: cn=admin,dc=puppetlabs,dc=test
roleOccupant: dc=puppetlabs,dc=test
objectClass: simpleSecurityObject
objectClass: organizationalRole
cn: admin
description: LDAP administrator
userPassword:: dGVzdA==

# people, puppetlabs.test
dn: ou=people,dc=puppetlabs,dc=test
objectClass: organizationalUnit
ou: people

# groups, puppetlabs.test
dn: ou=groups,dc=puppetlabs,dc=test
objectClass: organizationalUnit
ou: groups

# test, people, puppetlabs.test
dn: uid=test,ou=people,dc=puppetlabs,dc=test
objectClass: inetOrgPerson
uid: test
cn: I. Test User
cn: Test User
sn: User
mail: test@example.com
userPassword:: cGFzcw==

# search result
search: 2
result: 0 Success

# numResponses: 6
# numEntries: 5

