console.log(cursos);

const nomesFiltro = {
    codigo: "Código",
    nome: "Nome",
    evasaoMedia: "Evasão"
};

function getBarColor(evasao) {
    if (evasao > 70) return "bg-danger";
    if (evasao > 40) return "bg-warning";
    if (evasao > 20) return "bg-info";
    return "bg-success";
}

const tbody = document.getElementById("tabela-cursos");
const inputPesquisa = document.querySelector('.input-group input');

let cursosFiltrados = [...cursos];
let filtroAtual = 'evasaoMedia';
let ordemCrescente = false;

function renderTabela(lista) {
    tbody.innerHTML = '';
    lista.forEach(curso => {
        const row = document.createElement("tr");
        row.style.cursor = "pointer";
        row.addEventListener("click", () => {
            window.location.href = `/alunos/curso/${encodeURIComponent(curso.codigo)}`;
           // antes era window.location.href = `alunos_curso.html?codigo=${encodeURIComponent(curso.codigo)}`;
        });
        row.innerHTML = `
            <td>${curso.codigo}</td>
            <td>${curso.nome}</td>
            <td>
                <div class="progress">
                    <div class="progress-bar ${getBarColor(curso.evasaoMedia)}" style="width: ${curso.evasaoMedia}%; min-width: 60px;">
                        ${curso.evasaoMedia.toFixed(1).replace('.', ',')}%
                    </div>
                </div>
            </td>`;
        tbody.appendChild(row);
    });
}

function filtrarTabela() {
    const termo = inputPesquisa.value.trim().toLowerCase();
    cursosFiltrados = cursos.filter(curso =>
        curso.codigo.toString().toLowerCase().includes(termo) ||
        curso.nome.toLowerCase().includes(termo) ||
        curso.evasaoMedia.toFixed(1).replace('.', ',').includes(termo) ||
        curso.evasaoMedia.toFixed(1).includes(termo)
    );
    ordenarTabela();
}

function ordenarTabela() {
    cursosFiltrados.sort((a, b) => {
        let valA = a[filtroAtual];
        let valB = b[filtroAtual];

        if (filtroAtual === 'nome' || filtroAtual === 'codigo') {
            valA = valA.toString().toLowerCase();
            valB = valB.toString().toLowerCase();
            if (valA < valB) return ordemCrescente ? -1 : 1;
            if (valA > valB) return ordemCrescente ? 1 : -1;
            return 0;
        } else {
            return ordemCrescente ? valA - valB : valB - valA;
        }
    });
    renderTabela(cursosFiltrados);
}

function setFiltro(filtro, crescente) {
    filtroAtual = filtro;
    ordemCrescente = crescente;
    const ordem = ordemCrescente ? "↑" : "↓";
    document.getElementById('texto-filtro').textContent = `${nomesFiltro[filtro]} ${ordem}`;
    ordenarTabela();
}

// Eventos
inputPesquisa.addEventListener('input', filtrarTabela);

// Inicialização
ordenarTabela();
