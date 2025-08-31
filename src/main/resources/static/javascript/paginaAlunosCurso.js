console.log(alunos);

function getBarColor(evasao) {
    if (evasao > 0.7) return "bg-danger";
    if (evasao > 0.4) return "bg-warning";
    if (evasao > 0.2) return "bg-info";
    return "bg-success";
}

const tbody = document.getElementById("tabela-alunos");
const inputPesquisa = document.querySelector('.input-group input');

// Agora já vem filtrado pelo backend, basta copiar
let alunosFiltrados = [...alunos];
let filtroAtual = 'evasao';
let ordemCrescente = false;

// Definindo o título do curso dinamicamente com base no primeiro aluno
document.querySelector('.container h2')?.remove();
const titulo = document.createElement('h2');
titulo.className = "mb-4";
const nomeCurso = alunos.length > 0 ? alunos[0].nomeCurso : null;
titulo.textContent = nomeCurso ? `Alunos do curso: ${nomeCurso}` : "Curso não encontrado";
titulo.style.color = "#212529";
titulo.style.backgroundColor = "#fff";
titulo.style.padding = "0.5rem 1rem";
titulo.style.borderRadius = "0.25rem";
document.querySelector('.container').prepend(titulo);

// Função para calcular a média de probabilidade de evasão
function calcularMediaEvasao(historico) {
    if (!historico) return 0;
    const probabilidades = Object.values(historico);
    if (probabilidades.length === 0) return 0;
    return probabilidades.reduce((acc, curr) => acc + curr, 0) / probabilidades.length;
}

function renderTabela(lista) {
    tbody.innerHTML = '';
    lista.forEach(aluno => {
        // Se não tiver historico_probabilidade_evasao, usa evasao direto do DTO
        const mediaEvasao = aluno.historico_probabilidade_evasao
            ? calcularMediaEvasao(aluno.historico_probabilidade_evasao)
            : (aluno.evasao || 0);

        const row = document.createElement("tr");
        row.style.cursor = "pointer";
        row.addEventListener("click", () => {
            window.location.href = `/alunos/${encodeURIComponent(aluno.matricula)}`;
        });
        row.innerHTML = `
            <td>${aluno.nome}</td>
            <td>${aluno.matricula}</td>
            <td>
                <div class="progress">
                    <div class="progress-bar ${getBarColor(mediaEvasao/100)}" 
     style="width: ${mediaEvasao.toFixed(1)}%; min-width: 60px;">
    ${mediaEvasao.toFixed(1).replace('.', ',')}%
</div>

                </div>
            </td>`;
        tbody.appendChild(row);
    });
}

function filtrarTabela() {
    const termo = inputPesquisa.value.trim().toLowerCase();
    alunosFiltrados = alunos.filter(aluno =>
        aluno.nome.toLowerCase().includes(termo) ||
        String(aluno.matricula).includes(termo)
    );
    ordenarTabela();
}

function ordenarTabela() {
    alunosFiltrados.sort((a, b) => {
        let valA, valB;
        if (filtroAtual === 'nome') {
            valA = a.nome.toLowerCase();
            valB = b.nome.toLowerCase();
            if (valA < valB) return ordemCrescente ? -1 : 1;
            if (valA > valB) return ordemCrescente ? 1 : -1;
            return 0;
        } else if (filtroAtual === 'matricula') {
            valA = a.matricula;
            valB = b.matricula;
            return ordemCrescente ? valA - valB : valB - valA;
        } else if (filtroAtual === 'evasao') {
            valA = a.historico_probabilidade_evasao
                ? calcularMediaEvasao(a.historico_probabilidade_evasao)
                : (a.evasao || 0);
            valB = b.historico_probabilidade_evasao
                ? calcularMediaEvasao(b.historico_probabilidade_evasao)
                : (b.evasao || 0);
            return ordemCrescente ? valA - valB : valB - valA;
        }
        return 0;
    });
    renderTabela(alunosFiltrados);
}

function setFiltro(filtro, crescente) {
    filtroAtual = filtro;
    ordemCrescente = crescente;
    const nomesFiltro = {
        nome: "Nome",
        matricula: "Matrícula",
        evasao: "Evasão"
    };
    const ordem = ordemCrescente ? "↑" : "↓";
    document.getElementById('texto-filtro').textContent = `${nomesFiltro[filtro]} ${ordem}`;
    ordenarTabela();
}

// Eventos
inputPesquisa.addEventListener('input', filtrarTabela);

// Inicialização
ordenarTabela();
