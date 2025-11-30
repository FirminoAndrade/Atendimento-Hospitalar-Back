# Atendimento-Hospitalar-Back

Sistema completo para gerenciamento do fluxo hospitalar, incluindo:
- Cadastro de pacientes
- Triagem com classificação de risco
- Criação de consultas
- Atendimento médico
- Histórico de consultas por prontuário
- Integração entre front-end Angular e back-end Spring Boot
- Banco de dados PostgreSQL

---

## 📌 Funcionalidades Principais

### 👤 Pacientes
- Cadastro e listagem de pacientes
- Cada paciente possui um **prontuário único**
- Visualização de dados pessoais

### 🩺 Triagem
- Geração de uma nova consulta a partir do prontuário
- Classificação de risco
- Campos responsivos e estilizados (Angular Material)

### 👨‍⚕️ Consultas
- Listadas ao médico em tempo real
- Após o atendimento, a consulta sai da lista do dia
- Histórico completo vinculado ao prontuário

### 🗂 Relacionamento
- 1 Prontuário → muitas Consultas  
- Cada consulta pertence a um paciente

---

## 🛠 Tecnologias Utilizadas

### **Front-end**
- Angular 17+
- Angular Material
- TypeScript
- HTML / SCSS / Flex Layout
- Consumo de API REST

### **Back-end**
- Spring Boot 3+
- Spring Web
- Spring Data JPA
- PostgreSQL Driver
- Maven

### **Banco de Dados**
- PostgreSQL

### ▶ 1. Clonar o repositório
- ```bash
git clone https://github.com/FirminoAndrade/Atendimento-Hospitalar-Front.git
git clone https://github.com/FirminoAndrade/Atendimento-Hospitalar-Back.git
