console.log(alunos);

function getBarColor(evasao) {
    if (evasao > 0.7) return "bg-danger";
    if (evasao > 0.4) return "bg-warning";
    if (evasao > 0.2) return "bg-info";
    return "bg-success";
}

const tbody = document.getElementById("tabela-alunos");
const inputPesquisa = document.querySelector('.input-group input');

let alunosFiltrados = [...alunos];
let filtroAtual = 'evasao';
let ordemCrescente = false;

function renderTabela(lista) {
    tbody.innerHTML = '';
    lista.forEach(aluno => {
        const row = document.createElement("tr");
        row.style.cursor = "pointer";
        row.addEventListener("click", () => {
            window.location.href = `aluno.html?matricula=${encodeURIComponent(aluno.matricula)}`;
        });
        row.innerHTML = `
            <td>${aluno.nome}</td>
            <td>${aluno.nomeCurso}</td>
            <td>${aluno.matricula}</td>
            <td>
                <div class="progress">
                    <div class="progress-bar ${getBarColor(aluno.evasao / 100)}" 
                        style="width: ${aluno.evasao.toFixed(1)}%; min-width: 60px;">
                        ${aluno.evasao.toFixed(1).replace('.', ',')}%
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
        aluno.nomeCurso.toLowerCase().includes(termo) ||
        String(aluno.matricula).includes(termo) ||
        (aluno.evasao * 100).toFixed(1).replace('.', ',').includes(termo) ||
        (aluno.evasao * 100).toFixed(1).includes(termo)
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
            valA = a.evasao;
            valB = b.evasao;
            return ordemCrescente ? valA - valB : valB - valA;
        } else if (filtroAtual === 'curso') {
            valA = a.nomeCurso.toLowerCase();
            valB = b.nomeCurso.toLowerCase();
            if (valA < valB) return ordemCrescente ? -1 : 1;
            if (valA > valB) return ordemCrescente ? 1 : -1;
            return 0;
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
        evasao: "Evasão",
        curso: "Curso"
    };
    const ordem = ordemCrescente ? "↑" : "↓";
    document.getElementById('texto-filtro').textContent = `${nomesFiltro[filtro]} ${ordem}`;
    ordenarTabela();
}

// Eventos
inputPesquisa.addEventListener('input', filtrarTabela);

// Inicialização
ordenarTabela();
