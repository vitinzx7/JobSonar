import './App.css'
import { useState } from 'react';

function App() {
  const [searchQuery, setSearchQuery] = useState('')
  const [jobs, setJobs] = useState([])

  async function handleSearch(event) {
    event.preventDefault()

    const query = searchQuery.trim()

    if (!query) {
      return
    }

    const response = await fetch(
      `http://localhost:8080/jobs?query=${encodeURIComponent(query)}`
    )

    if (!response.ok) {
      throw new Error(`Failed to fetch jobs: ${response.status}`)
    }

    const jobResults = await response.json()
    setJobs(jobResults)
  }

  return (
    <main className="app-shell">
      <section className="search-section" aria-labelledby="page-title">
        <div className="brand-block">
          <p className="eyebrow">Job search radar</p>
          <h1 id="page-title">JobSonnar</h1>
          <p className="page-summary">
            Busque vagas por cargo e acompanhe os principais dados retornados pela API.
          </p>
        </div>

        <form className="search-form" aria-label="Busca de vagas" onSubmit={handleSearch}>
          <label className="search-label" htmlFor="job-query">
            Cargo
          </label>
          <div className="search-controls">
            <input
              id="job-query"
              className="search-input"
              type="search"
              name="query"
              placeholder="Ex: Java developer"
              autoComplete="off"
              value={searchQuery}
              onChange={(event) => setSearchQuery(event.target.value)}
            />
            <button className="search-button" type="submit">
              Buscar vagas
            </button>
          </div>
        </form>
      </section>

      <section className="results-section" aria-labelledby="results-title">
        <div className="results-header">
          <div>
            <p className="eyebrow">Results</p>
            <h2 id="results-title">Vagas encontradas</h2>
          </div>
          <span className="results-count">{jobs.length} vagas</span>
        </div>

        {jobs.length === 0 ? (
          <div className="empty-state">
            <p>Nenhuma busca realizada.</p>
          </div>
        ) : (
          <div className="jobs-list">
            {jobs.map((job) => (
              <article className="job-card" key={job.jobUrl}>
                <h3>{job.name}</h3>
                <p>{job.city || 'Local não informado'}</p>
                <p>{job.publishedDate || 'Data não informada'}</p>
              </article>
            ))}
          </div>
        )}
      </section>
    </main>
  )
}

export default App
