import './App.css'
import { useState } from 'react';

function isSafeJobUrl(jobUrl) {
  try {
    const url = new URL(jobUrl)
    const isGupyDomain =
      url.hostname === 'gupy.io' || url.hostname.endsWith('.gupy.io')

    return url.protocol === 'https:' && isGupyDomain
  } catch {
    return false
  }
}

function App() {
  const [searchQuery, setSearchQuery] = useState('')
  const [jobs, setJobs] = useState([])
  const [isLoading, setIsLoading] = useState(false)
  const [errorMessage, setErrorMessage] = useState('')
  const [hasSearched, setHasSearched] = useState (false)

  async function handleSearch(event) {
    event.preventDefault()

    const query = searchQuery.trim()

    if (!query) {
      return
    }
    setHasSearched(true)
    setErrorMessage('')
    setIsLoading(true)

    try {
      const response = await fetch(
        `http://localhost:8080/jobs?query=${encodeURIComponent(query)}`
      )

      if (!response.ok) {
        throw new Error(`Failed to fetch jobs: ${response.status}`)
      }

      const jobResults = await response.json()
      setJobs(jobResults)
    } catch(error) {
      console.error('Job search failed', error)
      setJobs([])
      setErrorMessage('Não foi possível buscar vagas. Tente novamente.')
    }
    finally {
      setIsLoading(false)
    }
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
            <button
              className="search-button"
              type="submit"
              disabled={isLoading}
            >
              {isLoading ? 'Buscando...' : 'Buscar vagas'}
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

        {errorMessage && (
          <div className="error-state" role="alert">
            <p>{errorMessage}</p>
          </div>
        )}

        {!errorMessage && jobs.length === 0 && (
          <div className="empty-state">
            <p>
              {hasSearched
              ? 'Nenhuma vaga encontrada.'
              : 'Nenhuma busca realizada.'}
          </p>
          </div>
        )}

        {!errorMessage && jobs.length > 0 && (
          <div className="jobs-list">
            {jobs.map((job) => (
              <article className="job-card" key={job.jobUrl}>
                <h3>{job.name}</h3>
                <p>{job.city || 'Local não informado'}</p>
                <p>{job.publishedDate || 'Data não informada'}</p>
                {isSafeJobUrl(job.jobUrl) ? (
                  <a
                    className="job-link"
                    href={job.jobUrl}
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    Ver vaga
                  </a>
                ) : (
                  <span className="job-link-unavailable">Link indisponível</span>
                )}
              </article>
            ))}
          </div>
        )}
      </section>
    </main>
  )
}

export default App
